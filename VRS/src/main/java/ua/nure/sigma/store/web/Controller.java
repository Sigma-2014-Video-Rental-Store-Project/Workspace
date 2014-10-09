package ua.nure.sigma.store.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sigma.store.web.command.Command;

/**
 * This class provides implementation of processing GET and POST HTTP methods.
 * All commands of this application context will be handled by this class at
 * first round of responsibility.
 *
 * Main purpose of this class is to define protocol of handling different
 * requests and providing unified response system.
 *
 * This class is also responsible for implementing PRG pattern.
 *
 * @author Denys Shevchenko
 * @author Maxim Sinkevich
 *
 * @version 1.0
 */
public final class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        process(request, response, false);
        LOG.debug("Do GET finished.");
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
     *
     * All GET requests are processed in 'Forward' mode, as the server must pass
     * call to the appropriate JSP page for formatting of representation layer.
     *
     * All POST requests are processed in 'Redirect' mode, as the server must
     * call appropriate {@code Command} to generate GET request. This structure
     * of calls is necessary because of PRG pattern implementation.
     *
     * @param request
     *            from the client.
     * @param response
     *            to the client.
     * @param redirect
     *            if there is a need to redirect request at the end (GET -
     *            false, POST - true).
     * @throws IOException
     *             connected with the implemented command execution.
     * @throws ServletException
     *             connected with the implemented command execution.
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
                RequestDispatcher disp = request.getRequestDispatcher(forward);
                disp.forward(request, response);
            }
        }
    }

}