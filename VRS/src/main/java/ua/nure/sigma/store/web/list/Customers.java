package ua.nure.sigma.store.web.list;

import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.logic.Pager;
import ua.nure.sigma.store.states.IListFilterState;
import ua.nure.sigma.store.web.Paths;

import java.util.List;

/**
 * Created by  on 20.10.14.
 */
public class Customers {

    private Pager pager;
    private List<Customer> originModel;
    IListFilterState<Customer> filterState;

    public Customers(List<Customer> customers){
        pager = new Pager(customers);
        originModel = customers;
        filterState = null;
    }

    /**
     *
     * @return customers on the page.
     */
    public List<Customer> getModel(){
        return (List<Customer>) pager.getModel();
    }



    /**
     *
     * @return list of all customers.
     */
    public List<Customer> getAllFilms(){
        return originModel;
    }

    public Pager getPager(){
        return pager;
    }


    public void setPageIndex(int index) {
        pager.setPageIndex(index);
    }

    public String getPageNumPrefix(){
        return Paths.COMMAND_FULL_FILM_LIST + "&" + "pageIndex=";
    }

    //Set filter that will filter the customers.
    public void setFilterState(IListFilterState<Customer> newState){
        filterState = newState;
        pager = new Pager(filterState.getFilteredList(originModel));
    }
}
