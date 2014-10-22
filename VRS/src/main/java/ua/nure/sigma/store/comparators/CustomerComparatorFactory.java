package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey Laposhko on 22.10.14.
 */
public class CustomerComparatorFactory {

    public static final String CUSTOMER_NAME_COMPARATOR = "name";
    public static final String CUSTOMER_RENTED_COPIES_COMPARATOR = "rentedCopies";
    public static final String CUSTOMER_RETURN_DATE_COMPARATOR = "returnDate";
    public static final String CUSTOMER_BONUS_COMPARATOR = "bonus";

    private static Map<String, Comparator<CustomerListItem>> comparatorMap;

    static {
        comparatorMap = new HashMap<String, Comparator<CustomerListItem>>();
        comparatorMap.put(CUSTOMER_NAME_COMPARATOR, new CustomerNameComparator());
        comparatorMap.put(CUSTOMER_RENTED_COPIES_COMPARATOR, new CustomerRentedCopiesComparator());
        comparatorMap.put(CUSTOMER_RETURN_DATE_COMPARATOR, new CustomerReturnDateComparator());
        comparatorMap.put(CUSTOMER_BONUS_COMPARATOR, new CustomerBonusComparator());
    }

    public static Comparator<CustomerListItem> getComparator(String sortName) {
        return comparatorMap.get(sortName);
    }

}
