package ua.nure.sigma.store.web.list;

import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.logic.Pager;
import ua.nure.sigma.store.states.IListFilterState;
import ua.nure.sigma.store.web.Paths;

import java.util.List;

/**
 * Created by Sergey Laposhko on 10.10.14.
 */
public class Films {
    private Pager pager;
    private List<Film> originModel;
    private IListFilterState<Film> filterState;

    public Films(List<Film> films) {
        pager = new Pager(films);
        originModel = films;
        filterState = null;
    }

    /**
     * @return films on the page.
     */
    public List<Film> getModel() {
        return (List<Film>) pager.getModel();
    }

    /**
     * @return list of films in selected category.
     */
    public List<Film> getAllFilms() {
        return originModel;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPageIndex(int index) {
        pager.setPageIndex(index);
    }

    public String getPageNumPrefix() {
        return Paths.COMMAND_FULL_FILM_LIST + "&" + "pageIndex=";
    }

    public void setFilterState(IListFilterState<Film> newState) {
        filterState = newState;
        pager = new Pager(filterState.getFilteredList(originModel));
    }

}
