package ua.nure.sigma.store.web.command.filmlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Films;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Laposhko on 10.10.14.
 */

public class FilmListCommand extends Command implements IComplexCommand {

    static final String FILMS_PARAM_NAME = "films";
    static final String CATEGORIES_PARAM_NAME = "categories";
    static final String PAGE_PARAM_NAME = "pageIndex";
    static final String SORT_PARAM_NAME = "sorting";
    static final String DIRECT_PARAM_NAME = "direct";
    static final String FILTER_PARAM_NAME = "filter";
    static final String ADD_TO_CART_ID_PARAM_NAME = "toCartId";
    public static final String CART_PARAM_NAME = "cart";

    private static final Logger LOG = org.apache.log4j.Logger.getLogger(FilmListCommand.class);

    List<Command> commandsListeners;

    FilmListCommand() {
        commandsListeners = new ArrayList<Command>();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Film list command started.");
        checkAndCreateFilmList(request, response);
        notifyAllCommands(request, response);
        LOG.debug("Film list command finished.");
        return Paths.PAGE_FILM_LIST;
    }

    private void checkAndCreateFilmList(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute(FILMS_PARAM_NAME) == null) {
            LOG.debug("Creating list of films");
            DAOFactory df = DAOFactory.getInstance();
            List<Film> films = df.getFilmDAO().findAllFilms();
            Films paramFilms = new Films(films);
            request.getSession().setAttribute(FILMS_PARAM_NAME, paramFilms);
        }
    }

    private void notifyAllCommands(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        for (Command command : commandsListeners) {
            command.execute(request, response);
        }
    }

    @Override
    public void addCommandListener(Command command) {
        commandsListeners.add(command);
    }
}
