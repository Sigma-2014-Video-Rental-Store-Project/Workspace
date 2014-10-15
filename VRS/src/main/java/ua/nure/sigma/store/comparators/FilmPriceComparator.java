package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.entity.Film;

import java.util.Comparator;

/**
 * Created by Sergey Laposhko on 15.10.14.
 */
public class FilmPriceComparator implements Comparator<Film> {

    /***
     * Compares 2 films by price
     * @param o1 film1
     * @param o2 film2
     * @return compare contract
     */
    @Override
    public int compare(Film o1, Film o2) {
        long res = o1.getRentPrice() - o2.getRentPrice();
        if(res == 0)
            return 0;
        if(res > 0)
            return 1;
        return -1;
    }

}
