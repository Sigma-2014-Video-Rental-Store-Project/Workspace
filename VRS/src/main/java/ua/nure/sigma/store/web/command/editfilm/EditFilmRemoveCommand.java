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
public class EditFilmRemoveCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filmIDString = request.getParameter(EditFilmCommand.FILMID_PARAM_NAME);
        String remove = request.getParameter(EditFilmCommand.REMOVE_PARAM_NAME);
        int filmId = 0;
        try {
            if (filmIDString != null) {
                filmId = Integer.parseInt(filmIDString);
            }

            if (Boolean.valueOf(remove)) {
                Film removeFilm = DAOFactory.getInstance().getFilmDAO().findFilmByID(filmId);
                DAOFactory.getInstance().getFilmDAO().deleteFilm(removeFilm);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
