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

	// Paths of the client side resources:
	public static final String PAGE_NO_PAGE = "/WEB-INF/jsp/no_page.jsp";
    public static final String PAGE_SIGN_IN = "/WEB-INF/jsp/sign_in.jsp";
    public static final String PAGE_FILM_LIST = "/WEB-INF/jsp/film_list.jsp";

    // Paths of the server command keys:
    public static final String COMMAND_SIGN_IN = "controller?command=signIn";
    public static final String COMMAND_ADMIN_VIEW = "controller?command=adminView";
    public static final String COMMAND_ROOT_VIEW = "controller?command=rootView";
    public static final String COMMAND_FULL_FILM_LIST = "controller?command=fullFilmList";
}