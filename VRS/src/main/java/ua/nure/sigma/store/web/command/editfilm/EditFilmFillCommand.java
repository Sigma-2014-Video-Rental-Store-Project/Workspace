package ua.nure.sigma.store.web.command.editfilm;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vlad Samotskiy on 18.10.14.
 */
public class EditFilmFillCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filmIDString = request.getParameter(EditFilmCommand.FILMID_PARAM_NAME);
        int filmId = 0;
        Film film = new Film();
        try {
            if (filmIDString != null) {
                filmId = Integer.parseInt(filmIDString);
                film = DAOFactory.getInstance().getFilmDAO().findFilmByID(filmId);            
            }
        request.getSession().setAttribute("editFilmObject", film);
        } catch (Exception e) {
        }
        return null;
    }
}
