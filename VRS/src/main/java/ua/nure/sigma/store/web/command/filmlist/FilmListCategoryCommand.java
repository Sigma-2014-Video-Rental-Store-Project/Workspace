package ua.nure.sigma.store.web.command.filmlist;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Categories;
import ua.nure.sigma.store.web.list.Films;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sergey Laposhko on 11.10.14.
 */
public class FilmListCategoryCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String categoriesString = (String) request.getParameter(FilmListCommand.CATEGORIES_PARAM_NAME);
        if (categoriesString != null && !categoriesString.equals("")) {
            List<Film> films =
                    DAOFactory.getInstance().getFilmCategoryDAO().findFilmsByCategoryID(Integer.parseInt(categoriesString));
            Films paramFilms = new Films(films);
            request.getSession().setAttribute(FilmListCommand.FILMS_PARAM_NAME, paramFilms);
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
            request.getSession().setAttribute(FilmListCommand.CATEGORIES_PARAM_NAME, paramCategories);
        }
        return null;
    }

}