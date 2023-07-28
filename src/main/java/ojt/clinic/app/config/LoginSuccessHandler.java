package ojt.clinic.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import ojt.clinic.app.bl.dto.UserAuthorityDetail;

/**
 * <h2>LoginSuccessHandler Class</h2>
 * <p>
 * Process for Displaying LoginSuccessHandler
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * <h2>onAuthenticationSuccess</h2>
     * <p>
     * Authentication Success
     * </p>
     * 
     * @param request        HttpServletRequest
     * @param response       HttpServletResponse
     * @param authentication Authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        UserAuthorityDetail userDetails = (UserAuthorityDetail) authentication.getPrincipal();
        int userId = userDetails.getUserId();
        HttpSession session = request.getSession();
        session.setAttribute("loggedInId", userId);
        String success = request.getContextPath() + "/dashboard";
        response.sendRedirect(success);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}