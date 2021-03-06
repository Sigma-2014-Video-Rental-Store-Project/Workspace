package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.entity.Film;

import java.util.Comparator;

/**
 * Created by Sergey Laposhko on 16.10.14.
 */
public class FilmCopiesLeftComparator implements Comparator<Film> {
    @Override
    public int compare(Film o1, Film o2) {
        if(o1.getCopiesLeft() > o2.getCopiesLeft())
            return 1;
        return o1.getCopiesLeft() < o2.getCopiesLeft() ? -1 : 0;
    }
}
