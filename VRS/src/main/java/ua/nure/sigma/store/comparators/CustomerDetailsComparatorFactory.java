package ua.nure.sigma.store.comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim Sinkevich on 31.10.2014.
 */
public class CustomerDetailsComparatorFactory {
    public static final String TITLE_COMPARATOR = "name";
    public static final String DAYS_LEFT_COMPARATOR = "daysLeft";
    public static final String END_DATE_COMPARATOR = "endDate";
    public static final String START_DATE_COMPARATOR = "startDate";
    public static final String STATUS_COMPARATOR = "status";

    private static Map<String, Comparator> comparators;

    static {
        comparators = new HashMap<String, Comparator>();
        comparators.put(TITLE_COMPARATOR, new CustomerDetailsTitleComparator());
        comparators.put(DAYS_LEFT_COMPARATOR, new CustomerDetailsDaysLeftComparator());
        comparators.put(END_DATE_COMPARATOR, new CustomerDetailsEndDateComparator());
        comparators.put(START_DATE_COMPARATOR, new CustomerDetailsStartDateComparator());
        comparators.put(STATUS_COMPARATOR, new CustomerDetailsStatusComparator());
    }

    /**
     * This function returns the comparator by sorting name.
     *
     * @param sortName may be "name", "price" or something else. You can get it from static fields of this class
     * @return Comparator that can compare films by name of sorting
     */
    public static Comparator getComparator(String sortName) {
        return comparators.get(sortName);
    }
}
