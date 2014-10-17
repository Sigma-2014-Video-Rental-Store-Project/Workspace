package ua.nure.sigma.store.web.command.filmlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.states.FilmListFilterAllState;
import ua.nure.sigma.store.states.FilmListFilterAvailableState;
import ua.nure.sigma.store.states.IListFilterState;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Films;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey Laposhko on 17.10.14.
 */
public class FilmListFilterCommand extends Command {
    private static final String ALL_FILTER_NAME = "all";
    private static final String AVAILABLE_FILTER_NAME = "available";

    private static final Logger LOG = Logger.getLogger(FilmListFilterCommand.class);

    private Map<String, IListFilterState<Film>> filterStateMap;
    {
        filterStateMap = new HashMap<String, IListFilterState<Film>>();
        filterStateMap.put(ALL_FILTER_NAME, new FilmListFilterAllState());
        filterStateMap.put(AVAILABLE_FILTER_NAME, new FilmListFilterAvailableState());
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filterParam = request.getParameter(FilmListCommand.FILTER_PARAM_NAME);

        LOG.debug("FilmListFilterCommand started with param = " + filterParam);

        if (filterParam != null && !filterParam.isEmpty()) {
            Films films = (Films) request.getSession().getAttribute(FilmListCommand.FILMS_PARAM_NAME);
            IListFilterState<Film> state = filterStateMap.get(filterParam);
            if(state != null){
                films.setFilterState(state);
                LOG.debug("Filtering succeed.");
            }
        }

        LOG.debug("FilmListCommand finished.");
        return null;
    }
}
