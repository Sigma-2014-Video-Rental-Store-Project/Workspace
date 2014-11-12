package ua.nure.sigma.store.states;

import ua.nure.sigma.store.entity.Film;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Laposhko on 17.10.14.
 */
public class FilmListFilterAvailableState implements IListFilterState<Film> {

    /**
     *
     * @param list list to be sorted.
     * @return returns list of available films.
     */
    @Override
    public List<Film> getFilteredList(List<Film> list) {
        List<Film> res = new ArrayList<Film>();
        for (Film aList : list) {
            if (aList.getCopiesLeft() > 0) {
                res.add(aList);
            }
        }
        return res;
    }
}