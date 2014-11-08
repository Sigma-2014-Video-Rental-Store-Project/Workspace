package ua.nure.sigma.store.web.filter;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.web.Paths;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Checks if the current request has rights to access the specified resource.
 * 
 * @author Denys Shevchenko
 * @version 1.0
 */
public class CommandAccessFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);

	// Commands access.
	private List<String> adminOnly = new ArrayList<String>();
	private List<String> commons = new ArrayList<String>();
	private List<String> outOfControl = new ArrayList<String>();

	/**
	 * Fulfills command containers of different rights.
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("Filter initialization starts");

		// Admin only.
		adminOnly = asList(fConfig.getInitParameter("admin"));
		LOG.trace("Admin only commands --> " + adminOnly);

		// Commons.
		commons = asList(fConfig.getInitParameter("common"));
		LOG.trace("Common commands --> " + commons);

		// Out of control.
		outOfControl = asList(fConfig.getInitParameter("out-of-control"));
		LOG.trace("Out of control commands --> " + outOfControl);

		LOG.debug("Filter initialization finished");
	}

	/**
	 * Checks rights of every request.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LOG.debug("Filter started");
		String redirect = getAccessRedirect(request);
		if (redirect == null) {
			LOG.debug("Filter finished");
			chain.doFilter(request, response);
		} else {
			LOG.trace("Filter finished: redirect --> " + redirect);
			request.getRequestDispatcher(redirect).forward(request, response);
		}
	}

	/**
	 * Checks rights of request and gets redirection URL if rights and resource
	 * don't match each other or 'null' is there is no need to redirect request.
	 * 
	 * @param request
	 *            that will be processed.
	 * @return redirection URL or 'null' is there is no need to redirect
	 *         request.
	 */
	private String getAccessRedirect(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String commandName = request.getParameter("command");
		LOG.trace("Requested command --> " + commandName);

		// Gets session (do not creates it if not exists).
		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			return null;
		}
		Admin user = (Admin) session.getAttribute("user");
		if (user == null) {
			return null;
		}
		if (outOfControl.contains(commandName)) {
			return Paths.PAGE_NO_PAGE;
		}
		if (adminOnly.contains(commandName) && user.getId() == 1) {
			return null;
		}
        if(commons.contains(commandName)){
            return null;
        }

        // If sign in by "Remember me".
        if(commandName == null){
            return Paths.COMMAND_FULL_FILM_LIST;
        }

        return Paths.PAGE_NO_PAGE;
	}

	@Override
	public void destroy() {
		LOG.debug("Filter destruction starts");
		LOG.debug("Filter destruction finished");
	}

	/**
	 * Extracts parameter values from string.
	 * 
	 * @param string
	 *            parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(String string) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(string);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}

}