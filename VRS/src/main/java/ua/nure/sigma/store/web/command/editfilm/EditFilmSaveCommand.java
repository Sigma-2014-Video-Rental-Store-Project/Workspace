package ua.nure.sigma.store.web.command.editfilm;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sinkevich Maksim on 14.10.2014.
 */
public class EditFilmSaveCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filmIDString = (String) request.getParameter(EditFilmCommand.FILMID_PARAM_NAME);
        String remove = (String) request.getParameter(EditFilmCommand.REMOVE_PARAM_NAME);
        int filmId = 0;
        if (filmIDString != null) {
            filmId = Integer.parseInt(filmIDString);
        }

        if (!Boolean.valueOf(remove)) {
            Film editingFilm = DAOFactory.getInstance().getFilmDAO().findFilmByID(filmId);
            if (editingFilm == null) {
                editingFilm = new Film();
                DAOFactory.getInstance().getFilmDAO().createFilm(editingFilm);
            }
            editingFilm.setTitle((String) request.getParameter(EditFilmCommand.FILM_TITLE_PARAM_NAME));
            //(String) request.getParameter(EditFilmCommand.FILM_CATEGORIES_PARAM_NAME);

            DAOFactory.getInstance().getFilmDAO().updateFilm(editingFilm);
        }

        return null;
    }
}
