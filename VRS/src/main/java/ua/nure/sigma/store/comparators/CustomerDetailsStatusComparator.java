package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.entity.CustomerDetails;

import java.util.Comparator;

/**
 * Created by Sergey Laposhko on 09.11.14.
 */
public class CustomerDetailsStatusComparator implements Comparator<CustomerDetails> {

    @Override
    public int compare(CustomerDetails o1, CustomerDetails o2) {
        if(o1.getAcceptedDate() == null && o2.getAcceptedDate() == null ||
                o1.getAcceptedDate() != null && o2.getAcceptedDate() != null){
            return 0;
        }
        if(o1.getAcceptedDate() == null){
            return 1;
        }
        return -1;
    }

}
