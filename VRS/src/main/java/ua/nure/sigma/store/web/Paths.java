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
    public static final String PAGE_EDIT_FILM = "/WEB-INF/jsp/edit_film.jsp";
    public static final String PAGE_CUSTOMER_LIST = "/WEB-INF/jsp/customer_list.jsp";
    public static final String PAGE_FILM_DETAILS = "/WEB-INF/jsp/film_details.jsp";
    public static final String PAGE_CART_DETAIL_FILMS = "/WEB-INF/jsp/cart_detail.jsp";
    public static final String PAGE_ADD_NEW_FILM = "/WEB-INF/jsp/add_new_film.jsp";
    public static final String PAGE_ADD_NEW_CUSTOMER = "/WEB-INF/jsp/add_customer.jsp";
    public static final String PAGE_EDIT_CUSTOMER = "/WEB-INF/jsp/edit_customer.jsp";
    public static final String PAGE_CUSTOMER_DETAILS = "/WEB-INF/jsp/customer_details.jsp";
    public static final String PAGE_ADMIN_LIST = "/WEB-INF/jsp/admin_list.jsp";
    public static final String PAGE_VIEW_ORDER_DETAIL = "/WEB-INF/jsp/orderDetails.jsp";
    public static final String PAGE_ADD_NEW_ADMIN = "/WEB-INF/jsp/add_new_admin.jsp";

    // Paths of the server command keys:
    public static final String COMMAND_SIGN_IN = "controller?command=signIn";
    public static final String COMMAND_FULL_FILM_LIST = "controller?command=fullFilmList";
    public static final String COMMAND_EDIT_FILM = "controller?command=editFilm";
    public static final String COMMAND_EDIT_CUSTOMER = "controller?command=editCustomer";
    public static final String COMMAND_ADD_NEW_CUSTOMER = "controller?command=addNewCustomer";
    public static final String COMMAND_ADD_NEW_FILM = "controller?command=addNewFilm";
    public static final String COMMAND_CART_DETAILS = "controller?command=cartDetails";
    public static final String COMMAND_CUSTOMER_DETAILS = "controller?command=customerDetails";
    public static final String COMMAND_ADMIN_LIST = "controller?command=adminList";
    public static final String COMMAND_CUSTOMER_LIST = "controller?command=customerList";
    public static final String COMMAND_ADD_NEW_ADMIN = "controller?command=addNewAdmin";
    public static final String COMMAND_ORDER_DETAIL = "controller?command=viewOrderDetails";
}