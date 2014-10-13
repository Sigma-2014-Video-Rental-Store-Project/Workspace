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

    void createRent(Film film,Customer customer, Date dueDate);
    void updateRent(int filmID, int customer_id, int copies);
    Customer findCustomerByRentID(long rentID);
    List<Rent> findRentByCustomerID(int id);
    int findCountOfRentedCopiesByFilmID(int film_id);
    void updateRentFilmCopy(int filmID, int customer_id, int copies);
}
