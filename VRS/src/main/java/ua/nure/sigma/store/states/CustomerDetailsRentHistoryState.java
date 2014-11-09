package ua.nure.sigma.store.states;

import ua.nure.sigma.store.entity.CustomerDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim Sinkevich on 29.10.2014.
 */
public class CustomerDetailsRentHistoryState implements IListFilterState<CustomerDetails> {
    @Override
    public List<CustomerDetails> getFilteredList(List<CustomerDetails> list) {
        List<CustomerDetails> res = new ArrayList<CustomerDetails>();
        for (CustomerDetails cd : list) {
            if (cd.getAcceptedDate() != null||cd.getFilmForRent().getCopies()!=cd.getCopiesLeft()) {
                res.add(cd);
            }
        }
        return res;
    }
}
