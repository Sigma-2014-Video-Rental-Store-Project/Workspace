package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import java.util.Comparator;

/**
 * Created by Sergey Laposhko on 22.10.14.
 */
public class CustomerReturnDateComparator implements Comparator<CustomerListItem> {

    @Override
    public int compare(CustomerListItem o1, CustomerListItem o2) {
        if(o1.getReturnDate() == null && o2.getReturnDate() == null)
            return 0;
        if(o1.getReturnDate() == null)
            return -1;
        if(o2.getReturnDate() == null)
            return 1;
        return o1.getReturnDate().compareTo(o2.getReturnDate());
    }

}
