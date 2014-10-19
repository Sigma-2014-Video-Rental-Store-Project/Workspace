package ua.nure.sigma.store.web.command.editfilm;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is responsible for deleting film with specified id from
 * the database. Notice, that {@code filmId} parameter is necessary to
 * be provided by calling request. It also must be {@code Integer} parsable
 * value.
 *
 * @author Maksim Sinkevich
 * @author Denys Shevchenko
 * @version 1.0
 */
public class EditFilmRemoveCommand extends Command {

    private static final Logger LOG = Logger.getLogger(EditFilmRemoveCommand.class);

    /**
     * Deletes {@code Film} object by the specified id from the current database.
     * Processes {@code null} id and unparsable id exceptions.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("EditFilmRemoveCommand started.");
        String filmIdString = request.getParameter(EditFilmCommand.FILM_ID_PARAM_NAME);

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

        // Tries to remove film with specified id.
        DAOFactory.getInstance().getFilmDAO().deleteFilm(filmId);
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Next film with id equals to '%d' has been deleted from the database.",
                    filmId));
            LOG.debug("EditFilmRemoveCommand finished.");
        }

        return Paths.COMMAND_FULL_FILM_LIST;
    }
}
