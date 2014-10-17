package ua.nure.sigma.store.comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey Laposhko on 15.10.14.
 */
public class FilmComparatorFactory {

    public static final String FILM_NAME_COMPARATOR = "name";
    public static final String FILM_PRICE_COMPARATOR = "price";
    public static final String FILM_COPIES_LEFT_COMPARATOR = "copies";

    private static Map<String, Comparator> comparators;
    static {
        comparators = new HashMap<String, Comparator>();
        comparators.put(FILM_NAME_COMPARATOR, new FilmNameComparator());
        comparators.put(FILM_PRICE_COMPARATOR, new FilmPriceComparator());
        comparators.put(FILM_COPIES_LEFT_COMPARATOR, new FilmCopiesLeftComparator());
    }

    /***
     * This function returns the comparator by sorting name.
     * @param sortName may be "name", "price" or something else. You can get it from static fields of this class
     * @return Comparator that can compare films by name of sorting
     */
    public static Comparator getComparator(String sortName){
        return comparators.get(sortName);
    }
}
