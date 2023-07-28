package ojt.clinic.app.web.controller.doctor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
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

import ojt.clinic.app.bl.dto.UserDTO;
import ojt.clinic.app.bl.service.doctor.DoctorService;
import ojt.clinic.app.bl.service.user.UserService;
import ojt.clinic.app.common.Constants;
import ojt.clinic.app.web.form.DoctorForm;

/**
 * <h2>DoctorController Class</h2>
 * <p>
 * Process for Displaying DoctorController
 * </p>
 * 
 * @author ZawLwinTun,Sai Zaw Myint
 *
 */
@Controller
public class DoctorController {
    /**
     * <h2>mv</h2>
     * <p>
     * mv
     * </p>
     */
    private ModelAndView mv;

    /**
     * <h2>doctorService</h2>
     * <p>
     * doctorService
     * </p>
     */
    @Autowired
    private DoctorService doctorService;

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

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
    HttpSession session;

    /**
     * <h2>context</h2>
     * <p>
     * context
     * </p>
     */
    @Autowired
    ServletContext context;

    /**
     * <h2>doctorRegistration</h2>
     * <p>
     * Doctor Registration
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/doctorInit")
    public ModelAndView doctorRegistration() {
        mv = new ModelAndView("doctor");
        mv.addObject("doctorForm", new DoctorForm());
        mv.addObject("btnName", "Clear");
        mv.addObject("btnJs", "clear");
        return mv;
    }

    /**
     * <h2>addDoctorConfirm</h2>
     * <p>
     * Confirm To Add Doctor
     * </p>
     *
     * @param doctorForm DoctorForm
     * @param br         BindingResult
     * @param profile    MultipartFile
     * @return ModelAndView
     */
    @RequestMapping(value = "/addDoctorConfirm", method = RequestMethod.POST)
    public ModelAndView addDoctorConfirm(@Valid @ModelAttribute("doctorForm") DoctorForm doctorForm, BindingResult br,
            @RequestParam("profile") MultipartFile profile) {
        mv = new ModelAndView();
        boolean isEmailExist;
        if (doctorForm.getUserId() == null || doctorForm.getUserId() == 0) {
            isEmailExist = userService.doCheckEmail(doctorForm.getUserEmail(), 0);
        } else {
            isEmailExist = userService.doCheckEmail(doctorForm.getUserEmail(), doctorForm.getUserId());
        }
        HashMap<String, String> data = doctorService.doAnalyseImageData(profile, doctorForm);
        if (br.hasErrors() || isEmailExist == true) {
            if (doctorForm.getUserId() == null || doctorForm.getUserId() == 0) {
                mv.addObject("btnName", "Clear");
                mv.addObject("btnJs", "clear");
            } else {
                mv.addObject("btnName", "Reset");
                mv.addObject("btnJs", "reset");
            }
            if (isEmailExist == true) {
                mv.addObject("msg", "Email already exists");
            }
            mv.setViewName("doctor");
        } else {
            mv.setViewName("confirm");
            mv.addObject("res", "doctor");
            doctorForm.setDoctorPhoto(data.get("filename"));
            mv.addObject("profileData", data.get("base64"));
        }
        return mv;
    }

    /**
     * <h2>addOrUpdateDoctor</h2>
     * <p>
     * Add Or Update Doctor
     * </p>
     *
     * @param doctorForm DoctorForm
     * @param profile    String
     * @return ModelAndView
     */
    @RequestMapping(value = "/addDoctor", method = RequestMethod.POST)
    public ModelAndView addOrUpdateDoctor(@ModelAttribute("doctorForm") DoctorForm doctorForm,
            @RequestParam("profile") String profile) {
        ModelAndView mv = new ModelAndView("redirect:/doctorView");
        String path = context.getRealPath("/resources/img/profile") + File.separator + doctorForm.getDoctorPhoto();
        try {
            doctorService.doUploadProfile(profile, path);
            if (doctorForm.getUserId() == null || doctorForm.getUserId() == 0) {
                doctorService.doAddDoctor(doctorForm);
                session.setAttribute("msg", messageSource.getMessage("M_SC_0001", null, null));
            } else {
                doctorService.doUpdateDoctor(doctorForm, doctorForm.getUserId());
                session.setAttribute("msg", messageSource.getMessage("M_SC_0003", null, null));
            }
        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("msg", "Cannot upload profile!");
        }
        return mv;
    }

    /**
     * <h2>doctorView</h2>
     * <p>
     * List Doctor
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/doctorView")
    public ModelAndView doctorView() {
        mv = new ModelAndView("doctorView");
        mv.addObject("title", "All Doctors");
        session.setAttribute("curPage", "doctorView");
        mv.addObject("doctorList", doctorService.doListDoctors(Constants.DOCTOR));
        return mv;
    }

    /**
     * <h2>doctorSearch</h2>
     * <p>
     * Search Doctor
     * </p>
     *
     * @param search String
     * @return ModelAndView
     */
    @RequestMapping(value = "/doctorSearch", method = RequestMethod.POST)
    public ModelAndView doctorSearch(@RequestParam("search") String search) {
        ModelAndView mv = new ModelAndView("doctorView");
        List<UserDTO> doctor = doctorService.doSearchListDoctors(search, Constants.DOCTOR);
        mv.addObject("doctorList", doctor);
        int count = doctor.size();
        mv.addObject("msg", count + " results found");
        return mv;
    }

    /**
     * <h2>editDoctor</h2>
     * <p>
     * Edit Doctor
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/editDoctor/{id}")
    public ModelAndView editDoctor(@PathVariable("id") Integer id) {
        mv = new ModelAndView("doctor");
        mv.addObject("doctorForm", doctorService.doGetDoctorById(id));
        mv.addObject("btnName", "Reset");
        mv.addObject("btnJs", "reset");
        return mv;
    }

    /**
     * <h2>deleteDoctor</h2>
     * <p>
     * Delete Doctor
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/deleteDoctor/{id}")
    public ModelAndView deleteDoctor(@PathVariable("id") Integer id) {
        mv = new ModelAndView("redirect:/doctorView");
        doctorService.doDeleteDoctor(id);
        session.setAttribute("msg", messageSource.getMessage("M_SC_0002", null, null));
        return mv;
    }

    /**
     * <h2>doctorSchedule</h2>
     * <p>
     * List Doctor Schedule
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/doctorSchedule")
    public ModelAndView doctorSchedule() {
        mv = new ModelAndView("doctorSchedule");
        Integer id = (int) session.getAttribute("loggedInId");
        session.setAttribute("curPage", "doctorSchedule");
        mv.addObject("doctorSchedule", doctorService.doGetDoctorSchedule(id));
        return mv;
    }

    /**
     * <h2>backDoctorRegistration</h2>
     * <p>
     * Back To Doctor Registration
     * </p>
     *
     * @return String
     */
    @RequestMapping(value = "/addDoctorConfirm", method = RequestMethod.POST, params = "back")
    public String backDoctorRegistration() {
        return "redirect:/doctorView";
    }

    /**
     * <h2>backDoctorConfirm</h2>
     * <p>
     * Back To Doctor Confirm
     * </p>
     *
     * @param doctorForm DoctorForm
     * @param profile
     * @return ModelAndView
     */
    @RequestMapping(value = "/addDoctor", method = RequestMethod.POST, params = "back")
    public ModelAndView backDoctorConfirm(@ModelAttribute("doctorForm") DoctorForm doctorForm,
            @RequestParam("profile") String profile) {
        mv = new ModelAndView("doctor");
        doctorForm.setDoctorPhoto(profile);
        mv.addObject("doctorForm", doctorForm);
        mv.addObject("btnName", "Clear");
        mv.addObject("btnJs", "clear");
        return mv;
    }
}