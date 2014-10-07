package ua.nure.sigma.store.web.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Common interface for the Command pattern implementation that provides
 * execution contract for the future implementations.
 * 
 * @author Denys Shevchenko
 * @version 1.0
 */
public abstract class Command implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Execution method for command. Must be overrided to provide handling of
	 * particular event. Parameters are passed through the {@code request}
	 * object.
	 * 
	 * @return URI to process when the command execution is complete.
	 */
	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException;

	/**
	 * Used for definition of class name to represent at logging of command
	 * execution processes. Class names of this pattern implementations must be
	 * appropriate to form clear log.
	 */
	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}

}