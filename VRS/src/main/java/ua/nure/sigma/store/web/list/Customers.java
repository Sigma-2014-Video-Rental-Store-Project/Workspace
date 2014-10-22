package ua.nure.sigma.store.web.list;

import ua.nure.sigma.store.logic.Pager;
import ua.nure.sigma.store.states.IListFilterState;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import java.util.List;

/**
 * Created by Sergey Laposhko on 20.10.14.
 */
public class Customers {

    private Pager pager;
    private List<CustomerListItem> originModel;
    IListFilterState<CustomerListItem> filterState;

    public Customers(List<CustomerListItem> customers){
        pager = new Pager(customers);
        originModel = customers;
        filterState = null;
    }

    /**
     *
     * @return customers on the page.
     */
    public List<CustomerListItem> getModel(){
        return (List<CustomerListItem>) pager.getModel();
    }

    /**
     *
     * @return list of all customers.
     */
    public List<CustomerListItem> getAllItems(){
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
    public void setFilterState(IListFilterState<CustomerListItem> newState){
        filterState = newState;
        pager = new Pager(filterState.getFilteredList(originModel));
    }
}
