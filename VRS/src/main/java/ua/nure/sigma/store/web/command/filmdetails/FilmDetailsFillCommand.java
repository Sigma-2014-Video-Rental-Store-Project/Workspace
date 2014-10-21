package ua.nure.sigma.store.web.command.filmdetails;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Categories;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vlad Samotskiy on 18.10.14.
 */
public class FilmDetailsFillCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filmIDString = request.getParameter(FilmDetailsCommand.FILMID_PARAM_NAME);
		List<Category> categories = PosgreSqlDAO.getInstance().getCategoryDAO().findAllCategory();
        int filmId = 0;
        Film film = new Film();
        try {
            if (filmIDString != null) {
                filmId = Integer.parseInt(filmIDString);
                film = DAOFactory.getInstance().getFilmDAO().findFilmById(filmId);
				List<Category> categoryOfCurrentFilmList = DAOFactory.getInstance().getFilmCategoryDAO().findCategoriesByFilmID(filmId);
				String cat = "";
				for(int i = 0; i < categoryOfCurrentFilmList.size(); i++){
					if(i<categoryOfCurrentFilmList.size()-1){
						cat += categoryOfCurrentFilmList.get(i).getName().toString() + ", ";
					}
					else{
						cat += categoryOfCurrentFilmList.get(i).getName().toString();
					}
				}
				Categories categoryOfCurrentFilm = new Categories(categoryOfCurrentFilmList);
				request.getSession().setAttribute("editFilmObject", film);
				request.getSession().setAttribute("filmCategory", categoryOfCurrentFilm);
				request.getSession().setAttribute("catString", cat);
            }
        } catch (Exception e) {
        }
		Categories paramCategories = new Categories(categories);
        request.getSession().setAttribute("categories", paramCategories);
        return null;
    }
}