package ua.nure.sigma.store.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sigma.store.web.Paths;

/**
 * This is a command that must be executed in case when some unprocessable
 * commands are tried to be executed. This is unified solution to process
 * situations when client must be informed about wrong command call.
 * 
 * @author Denys Shevchenko
 * @version 1.0
 * 
 * @see Command
 * @see Paths
 */
public class WrongCommand extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(WrongCommand.class);

	/**
	 * Logs warning about informing client about unprocessable command call and
	 * then redirects to error page to provide user-friendly explanation.
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.warn("Wrong command handler started.");
		LOG.warn("Wrong command handler finished.");

		return Paths.PAGE_NO_PAGE;
	}

}