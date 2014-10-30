package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.entity.CustomerDetails;

import java.util.Comparator;

/**
 * Created by Maksim Sinkevich on 30.10.2014.
 */
public class CustomerDetailsTitleComparator implements Comparator<CustomerDetails> {
    @Override
    public int compare(CustomerDetails o1, CustomerDetails o2) {
        if (o1.getName() == null && o2.getName() == null)
            return 0;
        if (o1.getName() == null)
            return -1;
        if (o2.getName() == null)
            return 1;
        return o1.getName().compareTo(o2.getName());
    }
}
