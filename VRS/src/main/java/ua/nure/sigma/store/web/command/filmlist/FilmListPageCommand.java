package ua.nure.sigma.store.web.command.filmlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Films;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sergey Laposhko on 11.10.14.
 */

public class FilmListPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FilmListPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageString = (String) request.getParameter(FilmListCommand.PAGE_PARAM_NAME);

        if (pageString != null && !pageString.equals("")) {
            //changing page index
            Films filmsParam = (Films) request.getSession().getAttribute(FilmListCommand.FILMS_PARAM_NAME);
            filmsParam.setPageIndex(Integer.valueOf(pageString));
            LOG.debug("Selected page = " + pageString);
        }

        return null;
    }

}
