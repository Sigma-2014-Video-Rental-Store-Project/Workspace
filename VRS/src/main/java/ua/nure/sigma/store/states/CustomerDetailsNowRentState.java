package ua.nure.sigma.store.states;

import ua.nure.sigma.store.entity.CustomerDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim Sinkevich on 29.10.2014.
 */
public class CustomerDetailsNowRentState implements IListFilterState<CustomerDetails> {

    @Override
    public List<CustomerDetails> getFilteredList(List<CustomerDetails> list) {
        List<CustomerDetails> res = new ArrayList<CustomerDetails>();
        for (CustomerDetails cd : list) {
            if (cd.getAcceptedDate() == null) {
                res.add(cd);
            }
        }
        return res;
    }
}
