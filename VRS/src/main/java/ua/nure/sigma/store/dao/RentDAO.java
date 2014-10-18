package ua.nure.sigma.store.dao;

import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.entity.Rent;

import java.util.Date;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public interface RentDAO {

    void createRent(Rent rent);
    Customer findCustomerByRentID(long rentID);
    List<Rent> findRentByCustomerID(int customerID);
    void closeRent(long rentID);
}
