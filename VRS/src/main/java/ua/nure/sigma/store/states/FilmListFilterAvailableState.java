package ua.nure.sigma.store.states;

import ua.nure.sigma.store.entity.Film;

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
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getCopiesLeft() <= 0){
                list.remove(i);
                i--;
            }
        }
        return list;
    }
}
