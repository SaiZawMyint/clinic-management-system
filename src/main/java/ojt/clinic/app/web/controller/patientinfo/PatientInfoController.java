package ojt.clinic.app.web.controller.patientinfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ojt.clinic.app.bl.dto.PatientInfoDTO;
import ojt.clinic.app.bl.service.doctor.DoctorService;
import ojt.clinic.app.bl.service.patientinfo.PatientInfoService;
import ojt.clinic.app.common.Constants;
import ojt.clinic.app.web.form.PatientInfoForm;

/**
 * <h2>PatientInfoController Class</h2>
 * <p>
 * Process for Displaying PatientInfoController
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Controller
public class PatientInfoController {
    /**
     * <h2>mv</h2>
     * <p>
     * mv
     * </p>
     */
    private ModelAndView mv;
    /**
     * <h2>patientInfoService</h2>
     * <p>
     * patientInfoService
     * </p>
     */
    @Autowired
    private PatientInfoService patientInfoService;

    /**
     * <h2>doctorService</h2>
     * <p>
     * doctorService
     * </p>
     */
    @Autowired
    private DoctorService doctorService;

    /**
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * <h2>session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private HttpSession session;

    /**
     * <h2>patientRegistration</h2>
     * <p>
     * Patient Registration
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/patientInit")
    public ModelAndView patientRegistration() {
        mv = new ModelAndView("patientInfo");
        mv.addObject("patientInfoForm", new PatientInfoForm());
        mv.addObject("doctorList", doctorService.doListDoctors(Constants.DOCTOR));
        mv.addObject("btnName", "Clear");
        mv.addObject("btnJs", "clear");
        return mv;
    }

    /**
     * <h2>addPatientInfoConfirm</h2>
     * <p>
     * Confirm To Add Patient Info
     * </p>
     *
     * @param patientInfoForm PatientInfoForm
     * @param br              BindingResult
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/addPatientInfoConfirm", method = RequestMethod.POST)
    public ModelAndView addPatientInfoConfirm(@Valid @ModelAttribute("patientInfoForm") PatientInfoForm patientInfoForm,
            BindingResult br) {
        mv = new ModelAndView();
        if (br.hasErrors()) {
            if (patientInfoForm.getPatientId() == null || patientInfoForm.getPatientId() == 0) {
                mv.addObject("btnName", "Clear");
                mv.addObject("btnJs", "clear");
            } else {
                mv.addObject("btnName", "Reset");
                mv.addObject("btnJs", "reset");
            }
            mv.setViewName("patientInfo");
            mv.addObject("doctorList", doctorService.doListDoctors(Constants.DOCTOR));
        } else {
            mv.setViewName("confirm");
            mv.addObject("res", "patientInfo");
        }
        return mv;
    }

    /**
     * <h2>addOeUpdatePatientInfo</h2>
     * <p>
     * Add Or Update Patient Info
     * </p>
     *
     * @param patientInfoForm PatientInfoForm
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/addPatientInfo", method = RequestMethod.POST)
    public ModelAndView addOeUpdatePatientInfo(@ModelAttribute("patientInfoForm") PatientInfoForm patientInfoForm) {
        mv = new ModelAndView("redirect:/patientInfoView");
        if (patientInfoForm.getPatientId() == null || patientInfoForm.getPatientId() == 0) {
            patientInfoService.doAddPatientInfo(patientInfoForm);
            session.setAttribute("msg", messageSource.getMessage("M_SC_0001", null, null));
        } else {
            patientInfoService.doUpdatePatientInfo(patientInfoForm.getPatientId(), patientInfoForm);
            session.setAttribute("msg", messageSource.getMessage("M_SC_0003", null, null));
        }
        return mv;
    }

    /**
     * <h2>addTodayPatient</h2>
     * <p>
     * Add Into Today Patient List
     * </p>
     *
     * @param id int
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/addTodayPatient/{id}")
    public ModelAndView addTodayPatient(@PathVariable("id") int id) {
        mv = new ModelAndView("addTodayPatient");
        mv.addObject("patientInfoForm", patientInfoService.doGetPatientInfoById(id));
        mv.addObject("doctorList", doctorService.doListDoctors(Constants.DOCTOR));
        return mv;
    }

    /**
     * <h2>addTDY</h2>
     * <p>
     * Add Today Patient
     * </p>
     *
     * @param patientInfoForm PatientInfoForm
     * @return ModelAndView
     */
    @RequestMapping(value = "/addTodayPatient", method = RequestMethod.POST)
    public ModelAndView addTDY(@ModelAttribute("patientInfoForm") PatientInfoForm patientInfoForm) {
        mv = new ModelAndView("redirect:/todayPatientView");
        patientInfoService.doAddTodayPatient(LocalDate.now(), patientInfoForm);
        session.setAttribute("msg", messageSource.getMessage("M_SC_0004", null, null));
        return mv;
    }

    /**
     * <h2>backTdy</h2>
     * <p>
     * Back To Today Patient
     * </p>
     *
     * @return String
     */
    @RequestMapping(value = "/addTodayPatient", method = RequestMethod.POST, params = "back")
    public String backTdy() {
        return "redirect:/todayPatientView";
    }

    /**
     * <h2>patientInfoView</h2>
     * <p>
     * List Patient Info
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/patientInfoView")
    public ModelAndView patientInfoView() {
        mv = new ModelAndView("patientInfoView");
        mv.addObject("today", LocalDate.now());
        mv.addObject("patientInfoList", patientInfoService.doListPatientInfos());
        session.setAttribute("curPage", "patientInfoView");
        return mv;
    }

    /**
     * <h2>patientInfoSearch</h2>
     * <p>
     * Search Patient Info
     * </p>
     *
     * @param search String
     * @return ModelAndView
     */
    @RequestMapping(value = "/patientInfoSearch", method = RequestMethod.POST)
    public ModelAndView patientInfoSearch(@RequestParam("search") String search) {
        mv = new ModelAndView("patientInfoView");
        List<PatientInfoDTO> patient = patientInfoService.doSearchListPatientInfos(search);
        mv.addObject("patientInfoList", patient);
        int count = patient.size();
        mv.addObject("msg", count + " results found");
        return mv;
    }

    /**
     * <h2>todayPatientView</h2>
     * <p>
     * List Today Patient
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/todayPatientView")
    public ModelAndView todayPatientView() {
        mv = new ModelAndView("todayPatientView");
        session.setAttribute("curPage", "todayPatientView");
        mv.addObject("treatingPatient", patientInfoService.doTodayPatients(LocalDate.now(), "Treating"));
        mv.addObject("finishedPatient", patientInfoService.doTodayPatients(LocalDate.now(), "Finished"));
        return mv;
    }

    /**
     * <h2>changeStatus</h2>
     * <p>
     * Change Status Of Patient
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/changeStatus/{id}")
    public ModelAndView changeStatus(@PathVariable("id") int id) {
        mv = new ModelAndView("redirect:/todayPatientView");
        patientInfoService.doChangeStatus(id);
        return mv;
    }

    /**
     * <h2>editPatientInfo</h2>
     * <p>
     * Edit Patient Info
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/editPatientInfo/{id}")
    public ModelAndView editPatientInfo(@PathVariable("id") int id) {
        mv = new ModelAndView("patientInfo");
        mv.addObject("btnName", "Reset");
        mv.addObject("btnJs", "reset");
        mv.addObject("patientInfoForm", patientInfoService.doGetPatientInfoById(id));
        mv.addObject("doctorList", doctorService.doListDoctors(Constants.DOCTOR));
        return mv;
    }

    /**
     * <h2>deletePatientInfo</h2>
     * <p>
     * Delete Patient Info
     * </p>
     *
     * @param id int
     * @return String
     */
    @RequestMapping("/deletePatientInfo/{id}")
    public String deletePatientInfo(@PathVariable("id") int id) {
        session.setAttribute("msg", this.messageSource.getMessage("M_SC_0002", null, null));
        patientInfoService.doDeletePatientInfo(id);
        return "redirect:/patientInfoView";
    }

    /**
     * <h2>downloadPatientInfo</h2>
     * <p>
     * Download Patient Info
     * </p>
     *
     * @param response HttpServletResponse
     * @throws IOException
     * @return void
     */
    @RequestMapping("/downloadPatientInfo")
    public void downloadPatientInfo(HttpServletResponse response) throws IOException {
        patientInfoService.doDownloadPatientInfo(response);
    }

    /**
     * <h2>uploadPatientInfoPage</h2>
     * <p>
     * Confirm To Upload Patient Info
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/uploadPatientInfoConfirm")
    public ModelAndView uploadPatientInfoPage() {
        mv = new ModelAndView("confirm");
        mv.addObject("res", "uploadPatientInfo");
        return mv;
    }

    /**
     * <h2>backUpload</h2>
     * <p>
     * Back To Upload
     * </p>
     *
     * @return String
     */
    @RequestMapping(value = "/uploadPatientInfo", method = RequestMethod.POST, params = "back")
    public String backUpload() {
        return "redirect:/patientInfoView";
    }

    /**
     * <h2>uploadPatientInfo</h2>
     * <p>
     * Upload Patient Info
     * </p>
     *
     * @param file MultipartFile
     * @return ModelAndView
     */
    @RequestMapping(value = "/uploadPatientInfo", method = RequestMethod.POST)
    public ModelAndView uploadPatientInfo(@RequestParam("file") MultipartFile file) {
        mv = new ModelAndView("redirect:/patientInfoView");
        String msg = "";
        try {
            msg = patientInfoService.doUploadPateintInfo(file);
            if (!msg.equalsIgnoreCase("File Upload Successful")) {
                mv.setViewName("confirm");
                mv.addObject("res", "uploadPatientInfo");
                mv.addObject("msg", messageSource.getMessage("M_ER_FILE_UPLOAD", null, null));
                mv.addObject("invoke", "Cannot upload file : Please check Excel File." + msg);
            } else {
                session.setAttribute("msg", msg);
            }
        } catch (IOException e) {
            mv.setViewName("confirm");
            mv.addObject("res", "uploadPatientInfo");
            mv.addObject("msg", messageSource.getMessage("M_ER_FILE_UPLOAD_01", null, null) + "<br>" + msg);
        }
        return mv;
    }

    /**
     * <h2>backPatientInfoRegistration</h2>
     * <p>
     * Back To PatientInfo Registration
     * </p>
     *
     * @return String
     */
    @RequestMapping(value = "/addPatientInfoConfirm", method = RequestMethod.POST, params = "back")
    public String backPatientInfoRegistration() {
        return "redirect:/patientInfoView";
    }

    /**
     * <h2>backPatientInfoConfirm</h2>
     * <p>
     * Back To PatientInfo Confirm
     * </p>
     *
     * @param form PatientInfoForm
     * @return ModelAndView
     */
    @RequestMapping(value = "/addPatientInfo", method = RequestMethod.POST, params = "back")
    public ModelAndView backPatientInfoConfirm(@ModelAttribute("patientInfoForm") PatientInfoForm form) {
        mv = new ModelAndView("patientInfo");
        mv.addObject("receptionistForm", form);
        if (form.getPatientId() == null || form.getPatientId() == 0) {
            mv.addObject("btnName", "Clear");
            mv.addObject("btnJs", "clear");
        } else {
            mv.addObject("btnName", "Reset");
            mv.addObject("btnJs", "reset");
        }
        return mv;
    }
}