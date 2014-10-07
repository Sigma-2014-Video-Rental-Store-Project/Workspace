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
 * Main servlet controller.
 * 
 * @author Denys Shevchenko
 * @author Maxim Sinkevich
 * @version 1.0
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(Controller.class);
	private final CommandKeeper commandKeeper = CommandKeeperInitializer
			.newCommandKeeper();

	/**
	 * Processes GET requests. Delegates on 'forward' option.
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("Do GET starts.");
		process(request, response, false);
		LOG.debug("Do GET finished.");
	}

	/**
	 * Processes POST requests. Delegates on 'redirect' option.
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Do POST starts.");
		process(request, response, true);
		LOG.trace("Do POST finished.");
	}

	/**
	 * Processes GET and POST requests.
	 * 
	 * @param request
	 *            of the user.
	 * @param response
	 *            to the user.
	 * @param redirect
	 *            if there is a need to redirect request at the end (GET -
	 *            false, POST - true).
	 * @throws IOException
	 *             connected with command execution.
	 * @throws ServletException
	 *             connected with command execution.
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response, boolean redirect) throws IOException,
			ServletException {
		LOG.debug("Controller starts.");

		// Extract command name from the request.
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);

		// Obtain command object by its name.
		Command command = commandKeeper.get(commandName);
		LOG.trace("Obtained command --> " + command);

		// Execute command and get forward address.
		String forward = command.execute(request, response);
		LOG.trace("Forward address --> " + forward);

		if (redirect) {
			LOG.debug("Controller finished, redirection on --> " + forward);
			response.sendRedirect(forward);
		} else {
			LOG.debug("Controller finished, now go to forward address --> "
					+ forward);

			// If the forward address is not null go to the address.
			if (forward != null) {
				LOG.debug("Query string --> " + request.getQueryString());
				RequestDispatcher disp = request.getRequestDispatcher(forward);
				disp.forward(request, response);
			}
		}
	}

}