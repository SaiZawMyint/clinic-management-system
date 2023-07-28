package ojt.clinic.app.web.controller.receptionist;

import java.util.List;

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

import ojt.clinic.app.bl.dto.UserDTO;
import ojt.clinic.app.bl.service.receptionist.ReceptionistService;
import ojt.clinic.app.bl.service.user.UserService;
import ojt.clinic.app.common.Constants;
import ojt.clinic.app.web.form.ReceptionistForm;

/**
 * <h2>ReceptionistController Class</h2>
 * <p>
 * Process for Displaying ReceptionistController
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
@Controller
public class ReceptionistController {

    /**
     * <h2>mv</h2>
     * <p>
     * mv
     * </p>
     */
    private ModelAndView mv;
    /**
     * <h2>receptionistService</h2>
     * <p>
     * receptionistService
     * </p>
     */
    @Autowired
    private ReceptionistService receptionistService;
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
    private HttpSession session;

    /**
     * <h2>receptionistRegistration</h2>
     * <p>
     * Receptionist Registration
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/receptionistInit")
    public ModelAndView receptionistRegistration() {
        mv = new ModelAndView("receptionist");
        mv.addObject("receptionistForm", new ReceptionistForm());
        mv.addObject("btnName", "Clear");
        mv.addObject("btnJs", "clear");
        return mv;
    }

    /**
     * <h2>addReceptionistConfirm</h2>
     * <p>
     * Confirm To Add Receptionist
     * </p>
     *
     * @param receptionistForm ReceptionistForm
     * @param br
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/addReceptionistConfirm", method = RequestMethod.POST)
    public ModelAndView addReceptionistConfirm(
            @Valid @ModelAttribute("receptionistForm") ReceptionistForm receptionistForm, BindingResult br) {
        mv = new ModelAndView();
        boolean isEmailExist;
        if (receptionistForm.getUserId() == null || receptionistForm.getUserId() == 0) {
            isEmailExist = userService.doCheckEmail(receptionistForm.getUserEmail(), 0);
        } else {
            isEmailExist = userService.doCheckEmail(receptionistForm.getUserEmail(), receptionistForm.getUserId());
        }
        if (br.hasErrors() || isEmailExist == true) {
            if (receptionistForm.getUserId() == null || receptionistForm.getUserId() == 0) {
                mv.addObject("btnName", "Clear");
                mv.addObject("btnJs", "clear");
            } else {
                mv.addObject("btnName", "Reset");
                mv.addObject("btnJs", "reset");
            }
            if (isEmailExist == true) {
                mv.addObject("msg", "Email already exists");
            }
            mv.setViewName("receptionist");
        } else {
            mv.setViewName("confirm");
            mv.addObject("res", "receptionist");
        }
        return mv;
    }

    /**
     * <h2>addOrUpdateReceptionist</h2>
     * <p>
     * Add Or Update Receptionist
     * </p>
     *
     * @param recepForm ReceptionistForm
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/addReceptionist", method = RequestMethod.POST)
    public ModelAndView addOrUpdateReceptionist(@ModelAttribute("receptionistForm") ReceptionistForm recepForm) {
        ModelAndView mv = new ModelAndView("redirect:/receptionistView");
        if (recepForm.getUserId() == null || recepForm.getUserId() == 0) {
            receptionistService.doAddReceptionist(recepForm);
            session.setAttribute("msg", messageSource.getMessage("M_SC_0001", null, null));
        } else {
            receptionistService.doUpdateReceptionist(recepForm);
            session.setAttribute("msg", messageSource.getMessage("M_SC_0003", null, null));
        }
        return mv;
    }

    /**
     * <h2>receptionistView</h2>
     * <p>
     * List Receptionist
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/receptionistView")
    public ModelAndView receptionistView() {
        mv = new ModelAndView("receptionistView");
        session.setAttribute("curPage", "receptionistView");
        mv.addObject("receptionistList", receptionistService.doListReceptionists(Constants.RECEPTIONIST));
        return mv;
    }

    /**
     * <h2>receptionistSearch</h2>
     * <p>
     * Search Receptionist
     * </p>
     *
     * @param search String
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/receptionistSearch", method = RequestMethod.POST)
    public ModelAndView receptionistSearch(@RequestParam("search") String search) {
        ModelAndView mv = new ModelAndView("receptionistView");
        List<UserDTO> recep = receptionistService.doSearchListReceptionists(search, Constants.RECEPTIONIST);
        mv.addObject("receptionistList", recep);
        int count = recep.size();
        mv.addObject("msg", count + " results found");
        return mv;
    }

    /**
     * <h2>editReceptionist</h2>
     * <p>
     * Edit Receptionist
     * </p>
     *
     * @param id int
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/editReceptionist/{id}")
    public ModelAndView editReceptionist(@PathVariable("id") Integer id) {
        mv = new ModelAndView("receptionist");
        mv.addObject("receptionistForm", receptionistService.doGetReceptionistById(id));
        mv.addObject("btnName", "Reset");
        mv.addObject("btnJs", "reset");
        return mv;
    }

    /**
     * <h2>deleteReceptionist</h2>
     * <p>
     * Delete Receptionist
     * </p>
     *
     * @param id int
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/deleteReceptionist/{id}")
    public ModelAndView deleteReceptionist(@PathVariable("id") Integer id) {
        mv = new ModelAndView("redirect:/receptionistView");
        receptionistService.doDeleteReceptionist(id);
        session.setAttribute("msg", messageSource.getMessage("M_SC_0002", null, null));
        return mv;
    }

    /**
     * <h2>backReceptionistRegistration</h2>
     * <p>
     * Back To Receptionist Registration
     * </p>
     *
     * @return String
     */
    @RequestMapping(value = "/addReceptionistConfirm", method = RequestMethod.POST, params = "back")
    public String backReceptionistRegistration() {
        return "redirect:/receptionistView";
    }

    /**
     * <h2>backReceptionistConfirm</h2>
     * <p>
     * Back To Receptionist Confirm
     * </p>
     *
     * @param form ReceptionistForm
     * @return ModelAndView
     */
    @RequestMapping(value = "/addReceptionist", method = RequestMethod.POST, params = "back")
    public ModelAndView backReceptionistConfirm(@ModelAttribute("receptionistForm") ReceptionistForm form) {
        mv = new ModelAndView("receptionist");
        mv.addObject("receptionistForm", form);
        mv.addObject("btnName", "Clear");
        mv.addObject("btnJs", "clear");
        return mv;
    }
}