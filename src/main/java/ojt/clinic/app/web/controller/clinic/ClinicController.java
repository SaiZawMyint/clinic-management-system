package ojt.clinic.app.web.controller.clinic;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ojt.clinic.app.bl.dto.UserDTO;
import ojt.clinic.app.bl.service.booking.BookingService;
import ojt.clinic.app.bl.service.doctor.DoctorService;
import ojt.clinic.app.bl.service.receptionist.ReceptionistService;
import ojt.clinic.app.common.Constants;

/**
 * <h2>ClinicController Class</h2>
 * <p>
 * Process for Displaying ClinicController
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
@Controller
public class ClinicController {
    /**
     * <h2>mv</h2>
     * <p>
     * mv
     * </p>
     */
    private ModelAndView mv;

    /**
     * <h2>bookingService</h2>
     * <p>
     * bookingService
     * </p>
     */
    @Autowired
    private BookingService bookingService;

    /**
     * <h2>doctorService</h2>
     * <p>
     * doctorService
     * </p>
     */
    @Autowired
    private DoctorService doctorService;

    /**
     * <h2>receptionistService</h2>
     * <p>
     * receptionistService
     * </p>
     */
    @Autowired
    private ReceptionistService receptionistService;

    /**
     * <h2>session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    HttpSession session;

    /**
     * <h2>initiateHomePage</h2>
     * <p>
     * Initiate Home Page
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/init")
    public ModelAndView initiateHomePage() {
        mv = new ModelAndView("init");
        mv.addObject("doctorList", doctorService.doListDoctors(Constants.DOCTOR));
        session.setAttribute("hdrActiver", "home");
        return mv;
    }

    /**
     * <h2>contact</h2>
     * <p>
     * Contact Us
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/contact")
    public ModelAndView contact() {
        mv = new ModelAndView("contact");
        session.setAttribute("hdrActiver", "contact");
        return mv;
    }

    /**
     * <h2>about</h2>
     * <p>
     * About Us
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/about")
    public ModelAndView about() {
        mv = new ModelAndView("about");
        session.setAttribute("hdrActiver", "about");
        return mv;
    }

    /**
     * <h2>login</h2>
     * <p>
     * Login
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/login")
    public ModelAndView login() {
        mv = new ModelAndView("login");
        session.setAttribute("hdrActiver", "login");
        return mv;
    }

    /**
     * <h2>dashboard</h2>
     * <p>
     * Dashboard
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/dashboard")
    public ModelAndView dashboard() {
        mv = new ModelAndView("dashboard");
        mv.addObject("activeClass", "home");
        session.setAttribute("hdrActiver", "dashboard");
        int userId = (int) session.getAttribute("loggedInId");
        UserDTO doctor = doctorService.doGetDoctorById(userId);
        UserDTO receptionist = receptionistService.doGetReceptionistById(userId);
        if (receptionist != null) {
            // for receptionist dashboard
            mv.addObject("bookingList", bookingService.doListBookings());
            mv.addObject("booking", "booking");
        }
        if (doctor != null) {
            // for doctor dashboard
            session.setAttribute("doctorInfo", doctorService.doGetDoctorById(userId));
            mv.addObject("treatingPatient", doctorService.doGetRelatedPatientInfo("Treating", userId));
            mv.addObject("finishedPatient", doctorService.doGetRelatedPatientInfo("Finished", userId));
        }
        session.setAttribute("curPage", "home");
        return mv;
    }

    /**
     * <h2>error</h2>
     * <p>
     * Error Or Access Denied Page
     * </p>
     *
     * @return String
     */
    @RequestMapping("/error")
    public String error() {
        return "user/access-denied";
    }
}
