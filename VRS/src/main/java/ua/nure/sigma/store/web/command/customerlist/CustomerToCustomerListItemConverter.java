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
        Date today = new Date();

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

    /**
     * This function is convertFromCustomersToCustomerListItems with LOGs and without committing result. Use it if you want to
     * debug.
     * @param customers list of customers that are the base for items.
     * @param listItems result list.
     */
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
                    Date mustReturnDate = filmForRent.getFutureDate();
                    Date returnDate = filmForRent.getAcceptedDate();
                    System.out.println("---customer id = " + customer.getCustomerID());
                    System.out.println("---rent start date = " + rent.getRentDate());
                    System.out.println("---mustReturnDate = " + mustReturnDate);
                    System.out.println("---returnDate = " + returnDate);
                    System.out.println("---film for rentID  = " + filmForRent.getFilmID());
                    System.out.println("----");
                    if (mustReturnDate.compareTo(today) > 0) {
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
            System.out.println("+++copies = " + copiesRented);
            System.out.println("+++rent date = " + minReturnDate);
            CustomerListItem item = new CustomerListItem(customer, minReturnDate, copiesRented);
        }
    }

}
