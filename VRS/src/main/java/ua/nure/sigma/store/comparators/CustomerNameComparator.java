package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import java.util.Comparator;

/**
 * Created by Sergey Laposhko on 22.10.14.
 */
public class CustomerNameComparator implements Comparator<CustomerListItem> {

    @Override
    public int compare(CustomerListItem o1, CustomerListItem o2) {
        if(o1.getLastName() == null && o2.getLastName() == null)
            return 0;
        if(o1.getLastName() == null)
            return -1;
        if(o2.getLastName() == null)
            return 1;
        return o1.getLastName().compareTo(o2.getLastName());
    }

}
