package ua.nure.sigma.store.web.command.filmlist;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Categories;
import ua.nure.sigma.store.web.list.Films;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 10.10.14.
 */
public class FilmListCommand extends Command implements IComplexCommand {
    static final String FILMS_PARAM_NAME = "films";
    static final String CATEGORIES_PARAM_NAME = "categories";
    static final String PAGE_PARAM_NAME = "pageIndex";

    List<Command> commandsListeners;

    FilmListCommand() {
        commandsListeners = new ArrayList<Command>();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        checkAndCreateFilmList(request, response);
        notifyAllCommands(request, response);
        return Paths.PAGE_FILM_LIST;
    }

    private void checkAndCreateFilmList(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute(FILMS_PARAM_NAME) == null) {
            DAOFactory df = DAOFactory.getInstance();
            List<Film> films = df.getFilmDAO().findAllFilms();
            if (films.size() >= 0) {
                //todo Delete it when db is filled
                for (int i = 0; i < 95; i++) {
                    Film film = new Film();
                    film.setTitle(String.valueOf(i));
                    film.setAmount(i);
                    films.add(film);
                }
            }
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
