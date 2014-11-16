package ua.nure.sigma.store.web.command.filmlist;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Categories;
import ua.nure.sigma.store.web.list.Films;

/**
 * Created by Sergey Laposhko on 11.10.14.
 */
public class FilmListCategoryCommand extends Command {

    static final String USER_PARAM_NAME = "user";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String categoriesString = (String) request.getParameter(FilmListCommand.CATEGORIES_PARAM_NAME);
        int categoriesId = 0;
        if (categoriesString != null) {
            categoriesId = Integer.parseInt(categoriesString);
        }
        Admin admin = (Admin) request.getSession().getAttribute(USER_PARAM_NAME);
        List<Category> categories =
        	DAOFactory.getInstance().getCategoryDAO().findAllCategory(admin.getLocale());
        if (categoriesId > 0 && categoriesId <= categories.size()) {
            List<Film> films =
                    DAOFactory.getInstance().getFilmCategoryDAO().findFilmsByCategoryID(categoriesId);
            Films paramFilms = new Films(films);
            request.getSession().setAttribute(FilmListCommand.FILMS_PARAM_NAME, paramFilms);
        } else {
            List<Film> films = DAOFactory.getInstance().getFilmDAO().findAllFilms();
            Films paramFilms = new Films(films);
            request.getSession().setAttribute(FilmListCommand.FILMS_PARAM_NAME, paramFilms);
        }
        Categories paramCategories = new Categories(categories);
        request.getSession().setAttribute(FilmListCommand.CATEGORIES_PARAM_NAME, paramCategories);
        return null;
    }

}
