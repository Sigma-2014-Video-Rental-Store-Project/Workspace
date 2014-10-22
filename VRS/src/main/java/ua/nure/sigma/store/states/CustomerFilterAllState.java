package ua.nure.sigma.store.states;

import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.web.list.ua.nure.sigma.store.web.list.entity.CustomerListItem;

import java.util.List;

/**
 * Created by Vasiliy Skidanenko on 20.10.14.
 */
public class CustomerFilterAllState implements IListFilterState<CustomerListItem> {
    @Override
    public List<CustomerListItem> getFilteredList(List<CustomerListItem> list) {
        return list;
    }
}
