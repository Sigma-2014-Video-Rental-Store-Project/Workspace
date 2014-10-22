package ua.nure.sigma.store.states;

import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasiliy Skidanenko on 20.10.14.
 */
public class CustomerFilterWithFilmsState implements IListFilterState<CustomerListItem> {
    @Override
    public List<CustomerListItem> getFilteredList(List<CustomerListItem> list) {
        List<CustomerListItem> res = new ArrayList<CustomerListItem>();

        for(CustomerListItem item : list){
            if(item.getCopiesRented() > 0)
                res.add(item);
        }

        return res;
    }
}
