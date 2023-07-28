package ojt.clinic.app.web.controller.booking;

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
import org.springframework.web.servlet.ModelAndView;

import ojt.clinic.app.bl.dto.BookingDTO;
import ojt.clinic.app.bl.dto.UserDTO;
import ojt.clinic.app.bl.service.booking.BookingService;
import ojt.clinic.app.bl.service.doctor.DoctorService;
import ojt.clinic.app.web.form.BookingForm;

/**
 * <h2>BookingController Class</h2>
 * <p>
 * Process for Displaying BookingController
 * </p>
 * 
 * @author ZawLwinTun,NweNi Aung
 *
 */
@Controller
public class BookingController {
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
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * <h2>doctorService</h2>
     * <p>
     * doctorService
     * </p>
     */
    @Autowired
    private DoctorService doctorService;

    /**
     * <h2>session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private HttpSession session;

    /**
     * <h2>bookingInit</h2>
     * <p>
     * Booking An Appointment
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/bookingInit/{id}")
    public ModelAndView bookingInit(@PathVariable("id") int id) {
        mv = new ModelAndView("booking");
        mv.addObject("bookingForm", new BookingForm());
        mv.addObject("btnName", "Clear");
        mv.addObject("btnJs", "clear");
        mv.addObject("userId", id);
        return mv;
    }

    /**
     * <h2>addBookingConfirm</h2>
     * <p>
     * Confirm To Add Booking
     * </p>
     *
     * @param bookingForm BookingForm
     * @param br          BindingResult
     * @return ModelAndView
     */
    @RequestMapping(value = "/addBookingConfirm", method = RequestMethod.POST)
    public ModelAndView addBookingConfirm(@Valid @ModelAttribute("bookingForm") BookingForm bookingForm,
            BindingResult br) {
        mv = new ModelAndView();
        if (br.hasErrors()) {
            if (bookingForm.getBookingId() == null || bookingForm.getBookingId() == 0) {
                mv.addObject("btnName", "Clear");
                mv.addObject("btnJs", "clear");
            } else {
                mv.addObject("btnName", "Reset");
                mv.addObject("btnJs", "reset");
            }
            mv.setViewName("booking");
        } else {
            mv.setViewName("confirm");
            UserDTO doctor = doctorService.doGetDoctorById(bookingForm.getUserId());
            mv.addObject("name", doctor.getUserName());
            mv.addObject("day", doctor.getDoctorDutyDay());
            mv.addObject("time", doctor.getDoctorDutyTime());
            mv.addObject("res", "booking");
        }
        return mv;
    }

    /**
     * <h2>addOrUpdateBooking</h2>
     * <p>
     * Add Or update Booking
     * </p>
     *
     * @param bookingForm BookingForm
     * @return ModelAndView
     */
    @RequestMapping(value = "/addBooking", method = RequestMethod.POST)
    public ModelAndView addOrUpdateBooking(@ModelAttribute("bookingForm") BookingForm bookingForm) {
        if (bookingForm.getBookingId() == null || bookingForm.getBookingId() == 0) {
            mv = new ModelAndView("redirect:/init");
            bookingService.doAddBooking(bookingForm);
            session.setAttribute("msg", messageSource.getMessage("M_SC_0001", null, null));
        } else {
            mv = new ModelAndView("redirect:/dashboard");
            bookingService.doUpdateBooking(bookingForm);
            session.setAttribute("msg", messageSource.getMessage("M_SC_0003", null, null));
        }
        return mv;
    }

    /**
     * <h2>bookingView</h2>
     * <p>
     * List Today Booking
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/bookingView")
    public ModelAndView bookingView() {
        mv = new ModelAndView("bookingView");
        mv.addObject("bookingList", bookingService.doListBookings());
        mv.addObject("booking", "booking");
        mv.addObject("bookingSearch", "bookingSearch");
        return mv;
    }

    /**
     * <h2>allBookingView</h2>
     * <p>
     * List All Booking
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/allBookingView")
    public ModelAndView allBookingView() {
        mv = new ModelAndView("bookingView");
        mv.addObject("bookingList", bookingService.doListAllBookings());
        mv.addObject("bookingSearch", "allBookingSearch");
        return mv;
    }

    /**
     * <h2>bookingSearch</h2>
     * <p>
     * Search Today Booking
     * </p>
     *
     * @param search String
     * @return ModelAndView
     */
    @RequestMapping(value = "/bookingSearch", method = RequestMethod.POST)
    public ModelAndView bookingSearch(@RequestParam("search") String search) {
        mv = new ModelAndView("bookingView");
        List<BookingDTO> bookingList = bookingService.doSearchListBookings(search);
        mv.addObject("bookingList", bookingList);
        int count = bookingList.size();
        mv.addObject("msg", count + " results found");
        mv.addObject("booking", "booking");
        return mv;
    }

    /**
     * <h2>allBookingSearch</h2>
     * <p>
     * Search All Booking
     * </p>
     *
     * @param search String
     * @return ModelAndView
     */
    @RequestMapping(value = "/allBookingSearch", method = RequestMethod.POST)
    public ModelAndView allBookingSearch(@RequestParam("search") String search) {
        mv = new ModelAndView("bookingView");
        List<BookingDTO> bookingList = bookingService.doSearchListAllBookings(search);
        mv.addObject("bookingList", bookingList);
        int count = bookingList.size();
        mv.addObject("msg", count + " results found");
        return mv;
    }

    /**
     * <h2>editBooking</h2>
     * <p>
     * Edit Booking
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/editBooking/{id}")
    public ModelAndView editBooking(@PathVariable("id") int id) {
        mv = new ModelAndView("booking");
        mv.addObject("btnName", "Reset");
        mv.addObject("btnJs", "reset");
        mv.addObject("bookingForm", bookingService.doGetBookingById(id));
        return mv;
    }

    /**
     * <h2>downloadBookingList</h2>
     * <p>
     * Download Booking
     * </p>
     *
     * @param response HttpServletResponse
     * @throws IOException
     * @return void
     */
    @RequestMapping(value = "/downloadBooking")
    public void downloadBookingList(HttpServletResponse response) throws IOException {
        bookingService.doDownlaodBooking(response);
    }

    /**
     * <h2>backBookingRegistration</h2>
     * <p>
     * Back To Booking Registration
     * </p>
     *
     * @param bookingForm BookingForm
     * @return String
     */
    @RequestMapping(value = "/addBookingConfirm", method = RequestMethod.POST, params = "back")
    public String backBookingRegistration(@ModelAttribute("bookingForm") BookingForm bookingForm) {
        if (bookingForm.getBookingId() == null || bookingForm.getBookingId() == 0)
            return "redirect:/init";
        else
            return "redirect:/dashboard";
    }

    /**
     * <h2>backBookingConfirm</h2>
     * <p>
     * Back To Booking Confirm
     * </p>
     *
     * @param form BookingForm
     * @return ModelAndView
     */
    @RequestMapping(value = "/addBooking", method = RequestMethod.POST, params = "back")
    public ModelAndView backBookingConfirm(@ModelAttribute("bookingForm") BookingForm form) {
        mv = new ModelAndView("booking");
        mv.addObject("bookingForm", form);
        if (form.getBookingId() == null || form.getBookingId() == 0) {
            mv.addObject("btnName", "Clear");
            mv.addObject("btnJs", "clear");
        } else {
            mv.addObject("btnName", "Reset");
            mv.addObject("btnJs", "reset");
        }
        return mv;
    }
}