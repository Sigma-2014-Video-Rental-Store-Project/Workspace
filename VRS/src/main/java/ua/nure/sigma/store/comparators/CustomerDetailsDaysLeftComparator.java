package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.entity.CustomerDetails;

import java.util.Comparator;

/**
 * Created by Maksim Sinkevich on 30.10.2014.
 */
public class CustomerDetailsDaysLeftComparator implements Comparator<CustomerDetails> {
    @Override
    public int compare(CustomerDetails o1, CustomerDetails o2) {
        if (o1.getDaysLeft() > o2.getDaysLeft())
            return 1;
        return o1.getDaysLeft() < o2.getDaysLeft() ? -1 : 0;
    }
}
