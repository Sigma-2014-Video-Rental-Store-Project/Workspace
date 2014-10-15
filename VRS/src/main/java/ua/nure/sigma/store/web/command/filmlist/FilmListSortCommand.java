package ua.nure.sigma.store.web.command.filmlist;

import ua.nure.sigma.store.comparators.FilmComparatorFactory;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;

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

    /***
     * Initiates the sorting action.
     * @param request request's session attribute "film list" can be changed.
     * @param response response
     * @return null
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Film> films = (List<Film>) request.getSession().getAttribute(FilmListCommand.FILMS_PARAM_NAME);
        Comparator<Film> comparator = FilmComparatorFactory.getComparator((String) request.getSession().getAttribute(FilmListCommand.SORT_PARAM_NAME));
        if(comparator == null)
            return null;

        Collections.sort(films, comparator);
        String direct = (String) request.getSession().getAttribute(FilmListCommand.DIRECT_PARAM_NAME);
        if(direct.equals(DOWN_DIR))
            Collections.reverse(films);

        request.getSession().setAttribute(FilmListCommand.FILMS_PARAM_NAME, films);
        return null;
    }
}
