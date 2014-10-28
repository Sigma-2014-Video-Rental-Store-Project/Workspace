package ua.nure.sigma.store.logic;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.*;
import ua.nure.sigma.store.states.IListFilterState;
import ua.nure.sigma.store.web.Paths;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim Sinkevich on 28.10.2014.
 */
public class ListForCustomerDetails {

    private static final long MILLISECONDS_IN_DAY = 86400000;

    private Pager pager;
    private Customer customer;
    private List<CustomerDetails> customerDetailsList;
    IListFilterState<CustomerDetails> filterState;

    public ListForCustomerDetails(Customer customer) {
        this.customer = customer;
        createModel(customer);
        pager = new Pager(customerDetailsList);
        filterState = null;
    }

    public List<CustomerDetails> getModel() {
        return (List<CustomerDetails>) pager.getModel();
    }

    public Pager getPager() {
        return pager;
    }

    public void setPageIndex(int index) {
        pager.setPageIndex(index);
    }

    public String getPageNumPrefix() {
        return Paths.COMMAND_CUSTOMER_DETAILS + "&" + "pageIndex=";
    }

    public void setFilterState(IListFilterState<Film> newState) {

    }

    private void createModel(Customer customer) {
        customerDetailsList = new ArrayList<CustomerDetails>();
        List<Rent> rents = DAOFactory.getInstance().getRentDAO().findRentByCustomerID(customer.getCustomerID());
        for (Rent r : rents) {
            for (FilmForRent f : r.getFilmList()) {
                CustomerDetails customerDetails = new CustomerDetails();
                customerDetails.setName(DAOFactory.getInstance().getFilmDAO().findFilmById(f.getFilmID()).getTitle());
                customerDetails.setStartDate(r.getRentDate());
                customerDetails.setEndDate(f.getAcceptedDate());
                customerDetails.setDaysLeft((int) ((f.getFutureDate().getTime() - r.getRentDate().getTime()) / MILLISECONDS_IN_DAY));
                customerDetailsList.add(customerDetails);
            }
        }
    }
}
