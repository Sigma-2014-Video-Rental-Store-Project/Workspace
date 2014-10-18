package ua.nure.sigma.store.web.command.editfilm;

import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Сергей on 18.10.14.
 */
public class EditFilmFillCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filmID = request.getParameter("id");
        //getting from dao film object
        Film film = new Film();

        request.getSession().setAttribute("editFilmObject", film);
        return null;
    }
}
