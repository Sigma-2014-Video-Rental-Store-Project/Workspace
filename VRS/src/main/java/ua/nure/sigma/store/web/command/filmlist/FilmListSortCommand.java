package ua.nure.sigma.store.web.command.filmlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.comparators.FilmComparatorFactory;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Films;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Sergey Lasposhko on 15.10.14.
 */
public class FilmListSortCommand extends Command {

    private static final String UP_DIR = "up";
    private static final String DOWN_DIR = "down";
    private static final Logger LOG = Logger.getLogger(FilmListSortCommand.class);

    /**
     * Initiates the sorting action.
     *
     * @param request  request's session attribute "film list" can be changed.
     * @param response response
     * @return null
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sortName = (String) request.getParameter(FilmListCommand.SORT_PARAM_NAME);
        String direct = (String) request.getParameter(FilmListCommand.DIRECT_PARAM_NAME);
        LOG.debug("Sorting command started.");
        if (sortName != null && direct != null) {
            LOG.debug("Sorting started with sortname = " + sortName + "; direct = " + direct + ".");

            List<Film> films = ((Films) request.getSession().getAttribute(FilmListCommand.FILMS_PARAM_NAME)).getAllFilms();
            Comparator<Film> comparator = FilmComparatorFactory.getComparator(sortName);
            if (comparator == null)
                return null;

            Collections.sort(films, comparator);
            if (direct.equals(DOWN_DIR))
                Collections.reverse(films);

 // TODO           request.getSession().setAttribute(FilmListCommand.FILMS_PARAM_NAME, new Films(films));
            LOG.debug("Sorting finished");
        }
        LOG.debug("Sorting command finished");
        return null;
    }
}
