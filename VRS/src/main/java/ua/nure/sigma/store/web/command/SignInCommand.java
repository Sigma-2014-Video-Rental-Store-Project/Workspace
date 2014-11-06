package ua.nure.sigma.store.web.command;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.AdminDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.logic.Cart;
import ua.nure.sigma.store.logic.CartCreator;
import ua.nure.sigma.store.web.Controller;
import ua.nure.sigma.store.web.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * This is a command that must be executed in case when user of the
 * application must sign in to the system. To get signed in, valid
 * email and password must be entered. Also provides cookie-based
 * sing in processing.
 *
 * @author Denys Shevchenko
 * @author Vlad Samotskiy
 * @version 1.0
 */
public class SignInCommand extends Command {

    private static final long serialVersionUID = 1L;
    private static final String COOKIE_LIFETIME_PARAMETER_NAME = "cookieLifetime";
    private static final String EMAIL_PARAMETER_NAME = "email";
    private static final String PASSWORD_PARAMETER_NAME = "password";
    private static final String REMEMBER_ME_PARAMETER_NAME = "remember-me";
    private static final String REMEMBER_ME_PARAMETER_VALUE = "remember";
    private static final String ERROR_CODE_PARAMETER_NAME = "&errorCode=";
    private static final Logger LOG = Logger.getLogger(SignInCommand.class);

    /**
     * Creates session if it has not been created yet. Gets email and password from
     * parameters. If one or both of them are empty or invalid, displays
     * specified error message. If everything is OK, then adjusts cookies if
     * necessary, and puts {@code Admin} object to the session.
     */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Sign in command started.");
        HttpSession session = request.getSession(true);

        // Obtain email and password from the request.
        String email = request.getParameter(EMAIL_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);

        // Error handler.
        String forward = Paths.COMMAND_SIGN_IN;

        // DB connector for the "admins" table.
        DAOFactory daoFactory = PosgreSqlDAO.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        if (email == null || password == null || email.isEmpty()
                || password.isEmpty()) {
            forward += ERROR_CODE_PARAMETER_NAME + 1;
            return forward;
        }

        // Gets User from the DB if exists.
        Admin admin = adminDAO.findAdminByLogin(email);
        if (admin == null || !(password.hashCode() == admin.getPassword())) {
            forward += ERROR_CODE_PARAMETER_NAME + 2;
            return forward;
        } else {
            String rememberMe = request.getParameter(REMEMBER_ME_PARAMETER_NAME);
            boolean rememberMeFlag = REMEMBER_ME_PARAMETER_VALUE.equals(rememberMe);
            forward = setUpAuthorization(session, admin, response, rememberMeFlag);
        }
        LOG.debug("Sign in command finished.");

        return forward;
    }

    /**
     * Adjusts current session attributes.
     *
     * @param session    that was created.
     * @param admin      that signed in to the system.
     * @param response   that might be used for setting cookies.
     * @param rememberMe flag - {@code true} if cookies should be used.
     * @return forward URI.
     */
    public static String setUpAuthorization(HttpSession session, Admin admin,
                                            HttpServletResponse response, boolean rememberMe) {
        LOG.debug("Authorization set up process started.");
        String forward = Paths.COMMAND_FULL_FILM_LIST;

        session.setAttribute(Controller.USER_ATTRIBUTE_NAME, admin);
        if (rememberMe) {

            // Default cookie lifetime.
            int lifetime = 3600;

            // Cookie lifetime in seconds.
            String cookieLifetime = session.getServletContext()
                    .getInitParameter(COOKIE_LIFETIME_PARAMETER_NAME);
            if (cookieLifetime == null) {
                LOG.warn("'Cookie lifetime' parameter was not initialized."
                        + " Default value was set up: " + lifetime);
            } else {
                lifetime = Integer.parseInt(cookieLifetime);
            }
            Cookie idCookie = new Cookie(Controller.USER_ID_COOKIE_NAME,
                    String.valueOf(admin.getId()));
            idCookie.setMaxAge(lifetime);
            response.addCookie(idCookie);
        }

        // Work with i18n.
        String userLocale = admin.getLocale();
        if (userLocale != null && !userLocale.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocale);
        }

        initCart(session);

        LOG.debug("Authorization set up process finished.");

        return forward;
    }

    /**
     * Supplies current user with {@code Cart} instance.
     * Adds it at the session context.
     *
     * @param session that will store reference on the cart.
     */
    private static void initCart(HttpSession session) {
        session.setAttribute(Cart.CART_ATTRIBUTE_NAME, CartCreator.newCart());
    }
}
