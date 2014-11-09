package ua.nure.sigma.store.web.command.editfilm;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Categories;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for creating edit form for
 * particular {@code Film} object and provide current data to
 * set in fields of this form.
 *
 * @author Maksim Sinkevich
 * @author Vlad Samotskiy
 * @author Denys Shevchenko
 * @version 1.0
 */
public class EditFilmCommand extends Command {

    public static final String FILM_ID_PARAM_NAME = "filmId";
    public static final String EDIT_FILM_OBJECT = "editFilmObject";
    private  static final String USER_PARAM_NAME = "user";

    private static final Logger LOG = Logger.getLogger(EditFilmCommand.class);

    /**
     * Processes request for the {@literal Edit Form} representation. Must be supplied
     * with {@code filmId} parameter through the {@code request}. Notice, that if there
     * is no {@code filmId} parameter at all or it cannot be parsed properly, this
     * command will redirect request on standard no page handler.
     */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("EditFilmCommand started.");

        // Must be specified with this command.
        String filmIdString = request.getParameter(FILM_ID_PARAM_NAME);

        // If this command has been called plain - return 'no page' handler.
        if (filmIdString == null) {
            LOG.warn("Cannot get 'filmId' parameter.");

            return Paths.PAGE_NO_PAGE;
        }
        int filmId;
        try {

            // Notice, that if this parameter cannot be parsed properly,
            // NumberFormatException will be thrown.
            filmId = Integer.parseInt(filmIdString);
        } catch (NumberFormatException e) {
            LOG.error("Cannot parse 'filmId' as Integer value.", e);

            return Paths.PAGE_NO_PAGE;
        }
        Film filmToEdit = DAOFactory.getInstance().getFilmDAO().findFilmById(filmId);

        // If the current database does not contain film with specified ID -
        // return 'no page' handler.
        if (filmToEdit == null) {
            LOG.warn(String.format("Cannot find film with id equals to %d in the database.", filmId));

            return Paths.PAGE_NO_PAGE;
        }
        
        Admin admin = (Admin) request.getSession().getAttribute(USER_PARAM_NAME);
        List<Category> categories =
                PosgreSqlDAO.getInstance().getCategoryDAO().findAllCategory(admin.getLocale());
        Categories paramCategories = new Categories(categories);
        request.setAttribute("categories", paramCategories);
        
        List<Category> categoryOfCurrentFilmList = DAOFactory.getInstance().getFilmCategoryDAO().findCategoriesByFilmID(filmId,admin.getLocale());
        Categories categoryOfCurrentFilm = new Categories(categoryOfCurrentFilmList);
        request.setAttribute("filmCategory", categoryOfCurrentFilm);

        request.setAttribute(EDIT_FILM_OBJECT, filmToEdit);
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Next film was set as '%s' attribute in the request scope: %s",
                    EDIT_FILM_OBJECT, filmToEdit));
            LOG.debug("EditFilmCommand finished.");
        }

        return Paths.PAGE_EDIT_FILM;
    }
}
