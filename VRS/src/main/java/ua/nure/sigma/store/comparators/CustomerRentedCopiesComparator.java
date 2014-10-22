package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import java.util.Comparator;

/**
 * Created by Sergey Laposhko on 22.10.14.
 */
public class CustomerRentedCopiesComparator implements Comparator<CustomerListItem> {

    @Override
    public int compare(CustomerListItem o1, CustomerListItem o2) {
        return o1.getCopiesRented() - o2.getCopiesRented();
    }

}
