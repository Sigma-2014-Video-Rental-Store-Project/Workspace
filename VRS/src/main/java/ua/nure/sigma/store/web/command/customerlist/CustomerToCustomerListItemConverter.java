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
 * Created by Sergey Laposhko on 22.10.14.
 */
public class CustomerToCustomerListItemConverter {
    
    /**
     * Converts and creates customer list items from simple customers. Adds copies rented and return date.
     * @param customers list of customers that are the base for items.
     * @param listItems result list.
     */
    public static void convertFromCustomersToCustomerListItems(List<Customer> customers, List<CustomerListItem> listItems) {
        RentDAO rentDAO = DAOFactory.getInstance().getRentDAO();
        FilmRentedDAO filmRentedDAO = DAOFactory.getInstance().getFilmRentedDAO();

        for (Customer customer : customers) {
            int copiesRented = 0;
            Date minReturnDate = null;

            for (Rent rent : rentDAO.findRentByCustomerID(customer.getCustomerID())) {
                for (FilmForRent filmForRent : filmRentedDAO.findFilmRentedByRentID(rent.getRentID())) {
                    Date mustReturnDate = filmForRent.getFutureDate();
                    Date returnedDate = filmForRent.getAcceptedDate();
                    if (mustReturnDate != null && returnedDate == null) {
                        copiesRented++; //Count of copies rented increases.

                        if (minReturnDate == null) // If we have no minDate, then set any return date that is more then today
                            minReturnDate = mustReturnDate;
                        else {
                            if (mustReturnDate.compareTo(minReturnDate) < 0)
                                minReturnDate = mustReturnDate;
                        }

                    }
                }
            }

            CustomerListItem item = new CustomerListItem(customer, minReturnDate, copiesRented);
            listItems.add(item);
        }
    }

}
