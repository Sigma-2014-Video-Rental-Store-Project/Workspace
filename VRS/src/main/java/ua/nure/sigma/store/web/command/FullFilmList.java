package ua.nure.sigma.store.web.command;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.list.Films;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Сергей on 10.10.14.
 */
public class FullFilmList extends Command {
    private static final String FILMS_PARAM_NAME = "films";
    private static final String CATEGORIES_PARAM_NAME = "categories";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Film> films = PosgreSqlDAO.getInstance().getFilmDAO().findAllFilms();
        if(films.size() == 0){
            //todo Delete it when db is filled
            for(int i = 0; i < 20; i++){
                Film film = new Film();
                film.setTitle(String.valueOf(i));
                film.setAmount(i);
                films.add(film);
            }
        }
        Films paramFilms = new Films(films);
        request.getSession().setAttribute(FILMS_PARAM_NAME, paramFilms);
        Film film = new Film();
        film.setTitle("myTitle");
        request.getSession().setAttribute("myfilm", film);

        List<Category> categories = PosgreSqlDAO.getInstance().getCategoryDAO().findAllCategory();
        return Paths.PAGE_FILM_LIST;
    }
}
