package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import java.util.Comparator;

/**
 * Created by Сергей on 22.10.14.
 */
public class CustomerReturnDateComparator implements Comparator<CustomerListItem> {

    @Override
    public int compare(CustomerListItem o1, CustomerListItem o2) {
        return o1.getReturnDate().compareTo(o2.getReturnDate());
    }

}
