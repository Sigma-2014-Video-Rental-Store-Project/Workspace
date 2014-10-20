package ua.nure.sigma.store.web.list;

import ua.nure.sigma.store.entity.Customer;

import java.util.List;

/**
 * Created by Maxim on 20.10.2014.
 */
public class Customers {
    private List<Customer> customers;

    public Customers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getModel() {
        return customers;
    }
}
