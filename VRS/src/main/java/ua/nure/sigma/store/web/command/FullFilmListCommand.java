package ua.nure.sigma.store.web.command;

import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.list.Categories;
import ua.nure.sigma.store.web.list.Films;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Сергей on 10.10.14.
 */
public class FullFilmListCommand extends Command {
    private static final String FILMS_PARAM_NAME = "films";
    private static final String CATEGORIES_PARAM_NAME = "categories";
    private static final String PAGE_PARAM_NAME = "pageIndex";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageString = (String) request.getParameter(PAGE_PARAM_NAME);
        System.out.println(pageString);
        if (pageString != null && !pageString.equals("")) {
            Films filmsParam = (Films) request.getSession().getAttribute(FILMS_PARAM_NAME);
            filmsParam.setPageIndex(Integer.valueOf(pageString));
        } else {
            List<Film> films = PosgreSqlDAO.getInstance().getFilmDAO().findAllFilms();
            if (films.size() == 0) {
                //todo Delete it when db is filled
                for (int i = 0; i < 20; i++) {
                    Film film = new Film();
                    film.setTitle(String.valueOf(i));
                    film.setAmount(i);
                    films.add(film);
                }
            }
            Films paramFilms = new Films(films);
            request.getSession().setAttribute(FILMS_PARAM_NAME, paramFilms);
        }

        String categoriesString = (String) request.getParameter(CATEGORIES_PARAM_NAME);
        if (categoriesString != null && !categoriesString.equals("")) {
            //todo filter films by category
        } else {
            List<Category> categories = PosgreSqlDAO.getInstance().getCategoryDAO().findAllCategory();
            if (categories.size() == 0) {
                //todo Delete it when db is filled
                for (int i = 0; i < 10; i++) {
                    Category cat = new Category();
                    cat.setName(String.valueOf(i));
                    cat.setId(i);
                    categories.add(cat);
                }
            }
            Categories paramCategories = new Categories(categories);
            request.getSession().setAttribute(CATEGORIES_PARAM_NAME, paramCategories);
        }
        return Paths.PAGE_FILM_LIST;
    }
}
