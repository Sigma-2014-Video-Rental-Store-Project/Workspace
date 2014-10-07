package ua.nure.sigma.store.web;

/**
 * This service class provides number of {@code String} constants each of what
 * represent path of JSP page, controller command, resource file etc.
 * 
 * @author Denys Shevchenko
 * @version 1.0
 */
public final class Paths {

	/**
	 * Protects service class from mistaken instantiation.
	 */
	private Paths() {

		// Must not be called.
		throw new AssertionError("Must not be instantiated.");
	}

	// Paths of the client side resources.
	public static final String PAGE_NO_PAGE = "/WEB-INF/jsp/no_page.jsp";

}