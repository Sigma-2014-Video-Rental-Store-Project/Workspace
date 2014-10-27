package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.entity.Film;

import java.util.Comparator;

/**
 * Created by Sergey Laposhko on 15.10.14.
 */
public class FilmNameComparator implements Comparator<Film> {

    /***
     * Compares 2 films by their title.
     * @param o1 the first film
     * @param o2 the second film
     * @return compare contract.
     */
    @Override
    public int compare(Film o1, Film o2) {
        if(o1.getTitle() == null && o2.getTitle() == null)
            return 0;
        if(o1.getTitle() == null)
            return -1;
        if(o2.getTitle() == null)
            return 1;
        return o1.getTitle().compareTo(o2.getTitle());
    }

}
