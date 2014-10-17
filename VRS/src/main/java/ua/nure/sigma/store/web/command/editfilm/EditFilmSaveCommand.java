package ua.nure.sigma.store.web.command.editfilm;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sinkevich Maksim on 14.10.2014.
 */
public class EditFilmSaveCommand extends Command {
    private static final Logger LOG = Logger.getLogger(EditFilmSaveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filmIDString = request.getParameter(EditFilmCommand.FILMID_PARAM_NAME);
        String remove = request.getParameter(EditFilmCommand.REMOVE_PARAM_NAME);
        int filmId = 0;
        if (filmIDString != null) {
            filmId = Integer.parseInt(filmIDString);
        }

        if (!Boolean.valueOf(remove)) {
            Film editingFilm = DAOFactory.getInstance().getFilmDAO().findFilmByID(filmId);
            if (editingFilm == null) {
                editingFilm = new Film();
            }

            try {
                String categoryName = request.getParameter(EditFilmCommand.FILM_CATEGORIES_PARAM_NAME);
                int categoryId = DAOFactory.getInstance().getCategoryDAO().findCategoryIdByName(categoryName);

                editingFilm.setTitle(request.getParameter(EditFilmCommand.FILM_TITLE_PARAM_NAME));
                editingFilm.setAmount(Integer.parseInt(request.getParameter(EditFilmCommand.FILM_AMOUNT_PARAM_NAME)));
                editingFilm.setDescription(request.getParameter(EditFilmCommand.FILM_DESCRIPTION_PARAM_NAME));
                editingFilm.setGeneralPrice(Long.parseLong(
                        request.getParameter(EditFilmCommand.FILM_GENERAL_PRICE_PARAM_NAME)));
                editingFilm.setRentPrice(Long.parseLong(
                        request.getParameter(EditFilmCommand.FILM_RENT_PRICE_PARAM_NAME)));
                editingFilm.setBonusForRent(Long.parseLong(
                        request.getParameter(EditFilmCommand.FILM_BONUS_PARAM_NAME)));
                editingFilm.setYear(Integer.parseInt(
                        request.getParameter(EditFilmCommand.FILM_YEAR_PARAM_NAME)));

                if (DAOFactory.getInstance().getFilmDAO().findFilmByID(filmId) == null) {
                    DAOFactory.getInstance().getFilmDAO().createFilm(editingFilm);
                    List<Film> filmList = DAOFactory.getInstance().getFilmDAO().findAllFilms();
                    filmId = filmList.get(filmList.size() - 1).getFilmId();
                    editingFilm.setCover(filmId + ".jpg");
                }
                editingFilm.setFilmId(filmId);
                DAOFactory.getInstance().getFilmCategoryDAO().createFilmCategory(editingFilm,
                        DAOFactory.getInstance().getCategoryDAO().findCategoryByID(categoryId));
                DAOFactory.getInstance().getFilmDAO().updateFilm(editingFilm);
            } catch (Exception e) {
            }
        }
        return null;
    }
}
