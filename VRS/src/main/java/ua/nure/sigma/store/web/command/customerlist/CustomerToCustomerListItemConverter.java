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
    public static final void convertFromCustomersToCustomerListItems(List<Customer> customers, List<CustomerListItem> listItems) {
        RentDAO rentDAO = DAOFactory.getInstance().getRentDAO();
        FilmRentedDAO filmRentedDAO = DAOFactory.getInstance().getFilmRentedDAO();
        Date today = new Date();

        //testLog(customers, listItems);TODO

        for (Customer customer : customers) {
            int copiesRented = 0;
            Date minReturnDate = null;

            for (Rent rent : rentDAO.findRentByCustomerID(customer.getCustomerID())) {
                for (FilmForRent filmForRent : filmRentedDAO.findFilmRentedByRentID(rent.getRentID())) {
                    Date rentDate = filmForRent.getFutureDate(); // TODO
                    if (rentDate != null && rentDate.compareTo(today) > 0) {
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

    private static void testLog(List<Customer> customers, List<CustomerListItem> listItems) {
        RentDAO rentDAO = DAOFactory.getInstance().getRentDAO();
        FilmRentedDAO filmRentedDAO = DAOFactory.getInstance().getFilmRentedDAO();
        Date today = new Date();

        for (Customer customer : customers) {
            int copiesRented = 0;
            Date minReturnDate = null;
            List<Rent> rents = rentDAO.findRentByCustomerID(customer.getCustomerID());
            if(rents.size() == 0){
                System.out.println("rents == 0 for customerid = " + customer.getCustomerID());
            }
            for (Rent rent : rents) {
                List<FilmForRent> filmForRentList = filmRentedDAO.findFilmRentedByRentID(rent.getRentID());
                if(filmForRentList.size() == 0){
                    System.out.println("filmForRentList == 0 for rentid = " + rent.getRentID());
                }
                for (FilmForRent filmForRent : filmForRentList) {
                    Date rentDate = filmForRent.getFutureDate(); // TODO
                    System.out.println("---customer id = " + customer.getCustomerID());
                    System.out.println("---rent date = " + rent.getRentDate());
                    System.out.println("---film for rentID  = " + filmForRent.getFilmID());
                    System.out.println("----");
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
            System.out.println("+++copies = " + copiesRented);
            System.out.println("+++rent date = " + minReturnDate);
            CustomerListItem item = new CustomerListItem(customer, minReturnDate, copiesRented);
            //listItems.add(item);
        }
    }

}
