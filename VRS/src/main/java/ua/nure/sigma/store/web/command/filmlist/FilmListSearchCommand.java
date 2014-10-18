package ua.nure.sigma.store.web.command.filmlist;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Categories;
import ua.nure.sigma.store.web.list.Films;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.UnsupportedEncodingException;

/**
 * Created by Vasiliy Skidanenko on 17.10.14.
 */
public class FilmListSearchCommand extends Command {

    private static final String SEARCH_PARAM_NAME = "key";
    private static final Logger LOG = Logger.getLogger(FilmListSortCommand.class);

    public static String decodeGetParameter(String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("ISO-8859-1"),"UTF8");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String searchName = (String) request.getParameter(SEARCH_PARAM_NAME);
        LOG.debug("Search command started.");

        if (searchName != null) {
            searchName = decodeGetParameter((String) request.getParameter(SEARCH_PARAM_NAME));
            LOG.debug("Search starts with arg = " + searchName + ".");
            int categoriesId = 0;
            List<Film> films = DAOFactory.getInstance().getFilmDAO().findAllFilms();
            List<Film> foundFilms = new ArrayList<Film>();
            for (int i=0;i<films.size();++i){
                if (films.get(i).getTitle().toLowerCase().contains(searchName.toLowerCase()))
                    foundFilms.add(films.get(i));
            }
            LOG.debug(foundFilms.size());
            Films paramFilms = new Films(foundFilms);
            request.getSession().setAttribute(FilmListCommand.FILMS_PARAM_NAME, paramFilms);
        }
        LOG.debug("Search command finished");

        return null;
    }

}        