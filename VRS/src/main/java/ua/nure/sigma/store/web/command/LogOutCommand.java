package ua.nure.sigma.store.web.command;

import ua.nure.sigma.store.web.Controller;
import ua.nure.sigma.store.web.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This is a command that must be executed in case when user of the
 * application must log out of the system.
 *
 * @author Vasiliy Skidanenko
 * @version 1.0
 */

public class LogOutCommand extends Command {

    /**
	 * Deletes cookie with current user's id and invalidates session. Then
	 * redirects on the authorization page.
	 */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        //LOG.debug("Log out command started.");
        
		HttpSession session = request.getSession();

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Controller.USER_ID_COOKIE_NAME.equals(cookie.getName())) {
					Cookie idCookie = new Cookie(Controller.USER_ID_COOKIE_NAME, "");
					idCookie.setMaxAge(0);
					response.addCookie(idCookie);
					break;
				}
			}
		}
		session.invalidate();
		
        //LOG.debug("Log out command finished.");

        return Paths.COMMAND_SIGN_IN ;
    }
}
