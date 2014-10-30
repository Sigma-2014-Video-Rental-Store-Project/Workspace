package ua.nure.sigma.store.logic;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.CustomerDetails;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.states.IListFilterState;
import ua.nure.sigma.store.web.Paths;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim Sinkevich on 28.10.2014.
 */
public class
        ListForCustomerDetails {

    private Pager pager;
    private Customer customer;
    private List<CustomerDetails> customerDetailsList;
    private IListFilterState<CustomerDetails> filterState;

    public ListForCustomerDetails(Customer customer) {
        this.customer = customer;
        createModel(customer);
        pager = new Pager(customerDetailsList);
        filterState = null;
    }

    /**
     * @return CustomerDetails on the page.
     */
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

    public void setFilterState(IListFilterState<CustomerDetails> newState) {
        filterState = newState;
        pager = new Pager(filterState.getFilteredList(customerDetailsList));
    }

    private void createModel(Customer customer) {
        customerDetailsList = new ArrayList<CustomerDetails>();
        List<Rent> rents = DAOFactory.getInstance().getRentDAO().findRentByCustomerID(customer.getCustomerID());
        for (Rent r : rents) {
            for (FilmForRent f : r.getFilmList()) {
                CustomerDetails customerDetails = new CustomerDetails();
                customerDetails.setFilm(DAOFactory.getInstance().getFilmDAO().findFilmById(f.getFilmID()));
                customerDetails.setFilmForRent(f);
                customerDetails.setRent(r);
                customerDetailsList.add(customerDetails);
            }
        }
    }
}
