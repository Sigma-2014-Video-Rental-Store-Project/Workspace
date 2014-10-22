package ua.nure.sigma.store.web.command.addNewFilm;

import org.apache.log4j.Logger;

import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Categories;
import ua.nure.sigma.store.web.command.editfilm.EditFilmCommand;

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
 * @author Vlad Samotskiy
 *
 * @version 1.0
 */
public class AddNewFilmCommand extends Command{
	 public static final String FILM_ID_PARAM_NAME = "filmId";

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
	        LOG.debug("AddNewFilmCommand started.");
	        
	        List<Category> categories = PosgreSqlDAO.getInstance().getCategoryDAO().findAllCategory();
	        Categories paramCategories = new Categories(categories);
	        request.setAttribute("categories", paramCategories);

	        return Paths.PAGE_ADD_NEW_FILM;
	    }
}
