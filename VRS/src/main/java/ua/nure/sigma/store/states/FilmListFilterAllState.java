package ua.nure.sigma.store.states;

import ua.nure.sigma.store.entity.Film;

import java.util.List;

/**
 * Created by Сергей on 17.10.14.
 */
public class FilmListFilterAllState implements IListFilterState<Film> {
    @Override
    public List<Film> getFilteredList(List<Film> list) {
        return list;
    }
}
