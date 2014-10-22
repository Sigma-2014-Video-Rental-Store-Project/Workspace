package ua.nure.sigma.store.web.command.customerlist;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmRentedDAO;
import ua.nure.sigma.store.dao.RentDAO;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 22.10.14.
 */
public class CustomerToCustomerListItemConverter {

    public static final void convertFromCustomersToCustomerListItems(List<Customer> customers, List<CustomerListItem> listItems) {
        RentDAO rentDAO = DAOFactory.getInstance().getRentDAO();
        FilmRentedDAO filmRentedDAO = DAOFactory.getInstance().getFilmRentedDAO();
        Date today = new Date();

        for (Customer customer : customers) {
            int copiesRented = 0;
            Date minReturnDate = null;

            for (Rent rent : rentDAO.findRentByCustomerID(customer.getCustomerID())) {
                for (FilmForRent filmForRent : filmRentedDAO.findFilmRentedByRentID(rent.getRentID())) {
                    Date rentDate = filmForRent.getFutureDate(); // TODO
                    if (rentDate.compareTo(today) > 0) {
                        copiesRented++; //Count of copies rented increases.

                        if (minReturnDate == null) // If we have no minDate, then set any return date that is more then today
                            minReturnDate = rentDate;
                        else {
                            if (rentDate.compareTo(minReturnDate) < 0)
                                minReturnDate = rentDate;
                        }

                    }
                }
            }

            CustomerListItem item = new CustomerListItem(customer, minReturnDate, copiesRented);
            listItems.add(item);
        }
    }

}
