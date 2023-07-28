package ojt.clinic.app.web.controller.patientmedicalrecord;

import java.io.IOException;
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
import ojt.clinic.app.bl.dto.PatientMedicalRecordDTO;
import ojt.clinic.app.bl.service.patientinfo.PatientInfoService;
import ojt.clinic.app.bl.service.patientmedicalrecord.PatientMedicalRecordService;
import ojt.clinic.app.web.form.PatientMedicalRecordForm;

/**
 * <h2>PatientMedicalRecordController Class</h2>
 * <p>
 * Process for Displaying PatientMedicalRecordController
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Controller
public class PatientMedicalRecordController {
    private ModelAndView mv;
    /**
     * <h2>patientMedicalRecordService</h2>
     * <p>
     * patientMedicalRecordService
     * </p>
     */
    @Autowired
    private PatientMedicalRecordService patientMedicalRecordService;

    /**
     * <h2>patientInfoService</h2>
     * <p>
     * patientInfoService
     * </p>
     */
    @Autowired
    private PatientInfoService patientInfoService;

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
     * <h2>addPatientMedicalRecordConfirm</h2>
     * <p>
     * Confirm To Add PatientMedicalRecord
     * </p>
     *
     * @param patientMedicalRecordForm PatientMedicalRecordForm
     * @param br                       BindingResult
     * @return ModelAndView
     */
    @RequestMapping(value = "/addPatientMedicalRecordConfirm", method = RequestMethod.POST)
    public ModelAndView addPatientMedicalRecordConfirm(
            @Valid @ModelAttribute("patientMedicalRecordForm") PatientMedicalRecordForm patientMedicalRecordForm,
            BindingResult br) {
        mv = new ModelAndView();
        if (br.hasErrors()) {
            if (patientMedicalRecordForm.getPatientMedicalRecordId() == null
                    || patientMedicalRecordForm.getPatientMedicalRecordId() == 0) {
                mv.addObject("btnName", "Clear");
                mv.addObject("btnJs", "clear");
            } else {
                mv.addObject("btnName", "Reset");
                mv.addObject("btnJs", "reset");
            }
            mv.setViewName("patientMedicalRecord");
        } else {
            mv.setViewName("confirm");
            mv.addObject("name",
                    patientInfoService.doGetPatientInfoById(patientMedicalRecordForm.getPatientId()).getPatientName());
            mv.addObject("res", "patientMedicalRecord");
        }
        return mv;
    }

    /**
     * <h2>addorUpdatePatientMedicalRecord</h2>
     * <p>
     * Add Or Update PatientMedicalRecord
     * </p>
     *
     * @param patientMedicalRecordForm PatientMedicalRecordForm
     * @return ModelAndView
     */
    @RequestMapping(value = "/addPatientMedicalRecord", method = RequestMethod.POST)
    public ModelAndView addorUpdatePatientMedicalRecord(
            @Valid @ModelAttribute("patientMedicalRecordForm") PatientMedicalRecordForm patientMedicalRecordForm) {
        mv = new ModelAndView("redirect:/medicalRecordView/" + patientMedicalRecordForm.getPatientId());
        if (patientMedicalRecordForm.getPatientMedicalRecordId() == null
                || patientMedicalRecordForm.getPatientMedicalRecordId() == 0) {
            patientMedicalRecordService.doAddPatientMedicalRecord(patientMedicalRecordForm);
            session.setAttribute("msg", messageSource.getMessage("M_SC_0001", null, null));
        } else {
            patientMedicalRecordService.doUpdatePatientMedicalRecord(patientMedicalRecordForm);
            session.setAttribute("msg", messageSource.getMessage("M_SC_0003", null, null));
        }
        return mv;
    }

    /**
     * <h2>addorUpdatePatientMedicalRecordById</h2>
     * <p>
     * Add or Update PatientMedicalRecord With Patient ID
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/addPatientMedicalRecord/{id}")
    public ModelAndView addorUpdatePatientMedicalRecordById(@PathVariable("id") int id) {
        mv = new ModelAndView("patientMedicalRecord");
        PatientMedicalRecordForm patientMedicalRecordForm = new PatientMedicalRecordForm();
        patientMedicalRecordForm.setPatientId(id);
        mv.addObject("patientMedicalRecordForm", patientMedicalRecordForm);
        mv.addObject("btnName", "Clear");
        mv.addObject("btnJs", "clear");
        return mv;
    }

    /**
     * <h2>medicalRecordView</h2>
     * <p>
     * List PatientMedicalRecord
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/medicalRecordView")
    public ModelAndView medicalRecordView() {
        ModelAndView mv = new ModelAndView("patientMedicalRecordView");
        mv.addObject("patientMedicalRecordList", patientMedicalRecordService.doListPatientMedicalRecords());
        session.setAttribute("curPage", "medicalRecordView");
        mv.addObject("all", "all");
        return mv;
    }

    /**
     * <h2>medicalRecordViewById</h2>
     * <p>
     * List PatientMedicalRecord By Patient ID
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/medicalRecordView/{id}")
    public ModelAndView medicalRecordViewById(@PathVariable("id") int id) {
        mv = new ModelAndView("patientMedicalRecordView");
        PatientInfoDTO patient = patientInfoService.doGetPatientInfoById(id);
        session.setAttribute("pid", id);
        mv.addObject("pid", id);
        mv.addObject("name", patient.getPatientName());
        mv.addObject("age", patient.getPatientAge());
        mv.addObject("gender", patient.getPatientGender());
        mv.addObject("patientMedicalRecordList", patientMedicalRecordService.doListPatientMedicalRecordsByPid(id));
        return mv;
    }

    /**
     * <h2>recordSearch</h2>
     * <p>
     * Search PatientMedicalRecord With Patient ID
     * </p>
     *
     * @param search String
     * @return ModelAndView
     */
    @RequestMapping(value = "/recordSearch", method = RequestMethod.POST)
    public ModelAndView recordSearch(@RequestParam("search") String search) {
        int pid = (int) session.getAttribute("pid");
        mv = new ModelAndView("patientMedicalRecordView");
        List<PatientMedicalRecordDTO> medicalRecord = patientMedicalRecordService.doSearchListPatientMedicalRecords(pid,
                search);
        mv.addObject("patientMedicalRecordList", medicalRecord);
        int count = medicalRecord.size();

        PatientInfoDTO patient = patientInfoService.doGetPatientInfoById(pid);
        mv.addObject("msg", count + " results found");
        mv.addObject("name", patient.getPatientName());
        mv.addObject("age", patient.getPatientAge());
        mv.addObject("gender", patient.getPatientGender());
        return mv;
    }

    /**
     * <h2>recordSearchAll</h2>
     * <p>
     * Search All PatientMedicalRecord
     * </p>
     *
     * @param search String
     * @return ModelAndView
     */
    @RequestMapping(value = "/recordSearchAll", method = RequestMethod.POST)
    public ModelAndView recordSearchAll(@RequestParam("search") String search) {
        mv = new ModelAndView("patientMedicalRecordView");
        List<PatientMedicalRecordDTO> medicalRecord = patientMedicalRecordService
                .doSearchAllListPatientMedicalRecords(search);
        mv.addObject("patientMedicalRecordList", medicalRecord);
        int count = medicalRecord.size();
        mv.addObject("msg", count + " results found");
        mv.addObject("all", "all");
        return mv;
    }

    /**
     * <h2>editPatientMedicalRecord</h2>
     * <p>
     * Edit PatientMedicalRecord
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/editPatientMedicalRecord/{id}")
    public ModelAndView editPatientMedicalRecord(@PathVariable("id") int id) {
        mv = new ModelAndView("patientMedicalRecord");
        mv.addObject("btnName", "Reset");
        mv.addObject("btnJs", "reset");
        mv.addObject("patientMedicalRecordForm", patientMedicalRecordService.doGetPatientMedicalRecordById(id));
        return mv;
    }

    /**
     * <h2>deletePatientMedicalRecord</h2>
     * <p>
     * Delete PatientMedicalRecord
     * </p>
     *
     * @param id int
     * @return String
     */
    @RequestMapping("/deletePatientMedicalRecord/{id}")
    public String deletePatientMedicalRecord(@PathVariable("id") int id) {
        session.setAttribute("msg", this.messageSource.getMessage("M_SC_0002", null, null));
        patientMedicalRecordService.doDeletePatientMedicalRecord(id);
        return "redirect:/medicalRecordView/"
                + patientMedicalRecordService.doGetPatientMedicalRecordById(id).getPatientId();
    }

    /**
     * <h2>downloadMedicalrecord</h2>
     * <p>
     * Download PatientMedicalRecord
     * </p>
     *
     * @param response HttpServletResponse
     * @throws IOException
     * @return void
     */
    @RequestMapping("/downloadMedicalRecord")
    public void downloadMedicalrecord(HttpServletResponse response) throws IOException {
        patientMedicalRecordService.doDownloadMedicalRecord(response);
    }

    /**
     * <h2>uploadPatientInfoPage</h2>
     * <p>
     * Confirm To Upload PatientMedicalRecord
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/uploadMedicalRecordConfirm")
    public ModelAndView uploadMedicalRecordConfirm() {
        mv = new ModelAndView("confirm");
        mv.addObject("res", "uploadPatientMedicalRecord");
        return mv;
    }

    /**
     * <h2>uploadMedicalRecord</h2>
     * <p>
     * Upload PatientMedicalRecord
     * </p>
     *
     * @param file MultipartFile
     * @return ModelAndView
     */
    @RequestMapping(value = "/uploadMedicalRecord", method = RequestMethod.POST)
    public ModelAndView uploadMedicalRecord(@RequestParam("file") MultipartFile file) {
        mv = new ModelAndView("redirect:/medicalRecordView");
        String msg = "";
        try {
            msg = patientMedicalRecordService.doUploadPatientMedicalRecord(file);
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
     * <h2>backUpload</h2>
     * <p>
     * Back To Upload
     * </p>
     *
     * @return String
     */
    @RequestMapping(value = "/uploadMedicalRecord", method = RequestMethod.POST, params = "back")
    public String backUpload() {
        return "redirect:/medicalRecordView";
    }

    /**
     * <h2>backPatientMedicalRecordRegistration</h2>
     * <p>
     * Back To Patient Medical Record Registration
     * </p>
     *
     * @param patientMedicalRecordForm PatientMedicalRecordForm
     * @return String
     */
    @RequestMapping(value = "/addPatientMedicalRecordConfirm", method = RequestMethod.POST, params = "back")
    public String backPatientMedicalRecordRegistration(
            @ModelAttribute("patientMedicalRecordForm") PatientMedicalRecordForm patientMedicalRecordForm) {
        return "redirect:/medicalRecordView/" + patientMedicalRecordForm.getPatientId();
    }

    /**
     * <h2>backPatientMedicalRecordConfirm</h2>
     * <p>
     * Back To Patient Medical Record Confirm
     * </p>
     *
     * @param form PatientMedicalRecordForm
     * @return ModelAndView
     */
    @RequestMapping(value = "/addPatientMedicalRecord", method = RequestMethod.POST, params = "back")
    public ModelAndView backPatientMedicalRecordConfirm(
            @ModelAttribute("patientMedicalRecordForm") PatientMedicalRecordForm form) {
        mv = new ModelAndView("patientMedicalRecord");
        mv.addObject("patientInfoForm", form);
        mv.addObject("btnName", "Clear");
        mv.addObject("btnJs", "clear");
        return mv;
    }
}