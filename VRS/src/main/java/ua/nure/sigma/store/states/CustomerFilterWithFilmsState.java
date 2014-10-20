package ua.nure.sigma.store.states;

import ua.nure.sigma.store.entity.Customer;

import java.util.List;

/**
 * Created by Сергей on 20.10.14.
 */
public class CustomerFilterWithFilmsState implements IListFilterState<Customer> {
    @Override
    public List<Customer> getFilteredList(List<Customer> list) {
        return null; //TODO
    }
}
