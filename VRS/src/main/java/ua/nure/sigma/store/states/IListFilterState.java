package ua.nure.sigma.store.states;

import ua.nure.sigma.store.entity.Film;

import java.util.List;

/**
 * Created by Sergey Laposhko on 17.10.14.
 */

/***
 * State for filtering entity list.
 * @param <T> type of entity.
 */
public interface IListFilterState<T> {
    public List<T> getFilteredList(List<T> list);
}
