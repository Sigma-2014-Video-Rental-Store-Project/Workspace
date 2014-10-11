package ua.nure.sigma.store.web;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.AdminDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.command.SignInCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * This class provides implementation of processing GET and POST HTTP methods.
 * All commands of this application context will be handled by this class at
 * first round of responsibility.
 * <p/>
 * Main purpose of this class is to define protocol of handling different
 * requests and providing unified response system.
 * <p/>
 * This class is also responsible for implementing PRG pattern.
 *
 * @author Denys Shevchenko
 * @author Maxim Sinkevich
 *
 * @version 1.0
 */
public final class Controller extends HttpServlet {

    public static final String USER_ATTRIBUTE_NAME = "user";
    public static final String USER_ID_COOKIE_NAME = "user-id";

    private static final int serialVersionUID = 1;
    private static final Logger LOG = Logger.getLogger(Controller.class);

    private final CommandKeeper commandKeeper;

    {
        // Initializes command keeper with list of predefined commands and
        // String key to each of them.
        commandKeeper = CommandKeeperInitializer.newCommandKeeper();
    }

    /**
     * Processes GET requests. Delegates to 'forward' option.
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Do GET started.");
        String redirection = preProcess(request, response);
        if (redirection == null) {
            process(request, response, false);
            LOG.debug("Do GET finished: forward.");
        } else {
            LOG.debug("Do GET finished: redirect.");
            RequestDispatcher dispatcher = request.getRequestDispatcher(redirection);
            dispatcher.forward(request, response);
        }
    }

    /**
     * Checks if the user authorized. If not, checks Cookies. If nothing
     * has been found redirects on authorization page.
     *
     * @param request  of the user.
     * @param response to the user.
     * @return redirection URL or 'null' if there is no need to redirect.
     */
    private String preProcess(HttpServletRequest request,
                              HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(USER_ATTRIBUTE_NAME) != null) {
            return null;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (USER_ID_COOKIE_NAME.equals(cookie.getName())) {
                    int id = Integer.parseInt(cookie.getValue());
                    DAOFactory daoFactory = PosgreSqlDAO.getInstance();
                    AdminDAO adminDAO = daoFactory.getAdminDAO();
                    Admin user = adminDAO.findAdminById(id);
                    if (user == null) {
                        return Paths.PAGE_NO_PAGE;
                    }
                    session = request.getSession(true);

                    return SignInCommand.setUpAuthorization(session, user, response,
                            true);
                }
            }
        }

        return Paths.PAGE_SIGN_IN;
    }

    /**
     * Processes POST requests. Delegates to 'redirect' option.
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        LOG.trace("Do POST started.");
        process(request, response, true);
        LOG.trace("Do POST finished.");
    }

    /**
     * Processes GET and POST requests in a unified way.
     * <p/>
     * All GET requests are processed in 'Forward' mode, as the server must pass
     * call to the appropriate JSP page for formatting of representation layer.
     * <p/>
     * All POST requests are processed in 'Redirect' mode, as the server must
     * call appropriate {@code Command} to generate GET request. This structure
     * of calls is necessary because of PRG pattern implementation.
     *
     * @param request  from the client.
     * @param response to the client.
     * @param redirect if there is a need to redirect request at the end (GET -
     *                 false, POST - true).
     * @throws IOException      connected with the implemented command execution.
     * @throws ServletException connected with the implemented command execution.
     */
    private void process(HttpServletRequest request,
                         HttpServletResponse response, boolean redirect) throws IOException,
            ServletException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Controller started.");
        }

        // Extracts command name from the request.
        String commandName = request.getParameter("command");
        if (LOG.isTraceEnabled()) {
            LOG.trace("Request parameter: command --> " + commandName);
        }

        // Obtains command object by its name.
        Command command = commandKeeper.get(commandName);
        if (LOG.isTraceEnabled()) {
            LOG.trace("Obtained command --> " + command);
        }

        // Executes command and gets forward address.
        String forward = command.execute(request, response);
        if (LOG.isTraceEnabled()) {
            LOG.trace("Forward address --> " + forward);
        }

        if (redirect) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Controller finished, redirection on --> " + forward);
            }
            response.sendRedirect(forward);
        } else {

            // If the forward address is not 'null', then go to the address.
            if (forward != null) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Controller finished, forward on --> " + forward);
                    LOG.debug("Query string --> " + request.getQueryString());
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
                dispatcher.forward(request, response);
            }
        }
    }

}