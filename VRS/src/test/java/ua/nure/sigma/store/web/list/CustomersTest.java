package ua.nure.sigma.store.web.list;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.states.CustomerFilterAllState;
import ua.nure.sigma.store.states.IListFilterState;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

public class CustomersTest {

    @Test
    public void testCustomers() throws Exception {
	new Customers(new ArrayList<CustomerListItem>());
    }

    @Test
    public void testGetModel() throws Exception {
	Customers customers = getCustomersWithSize(100);
	List<CustomerListItem> model = customers.getModel();
	Assert.assertEquals("Model doesn't contain 10 elements", 10, model.size());
    }

    @Test
    public void testGetAllItems() throws Exception {
	Customers customers = getCustomersWithSize(100);
	List<CustomerListItem> allItems = customers.getAllItems();
	Assert.assertEquals("All elements doesn't contain all elements", 100, allItems.size());
    }

    @Test
    public void testGetPager() throws Exception {
	List<CustomerListItem> list = new ArrayList<CustomerListItem>();
	for(int i = 0; i < 100; i++){
	    list.add(new CustomerListItem(new Customer(), null, 0));
	}
	Customers customers = new Customers(list);
	Assert.assertNotNull("Customers return null pager.", customers.getPager());
    }

    @Test
    public void testSetPageIndex() throws Exception {
	Customers customers = getCustomersWithSize(100);
	customers.setPageIndex(5);
	Assert.assertEquals("SetPageIndex doesn't work.", 5, customers.getPager().getPageIndex());
    }

    @Test
    public void testSetFilterState() throws Exception {
	Customers customers = getCustomersWithSize(10);
	IListFilterState<CustomerListItem> state = new CustomerFilterAllState();
	customers.setFilterState(state);
	Assert.assertEquals("Can't set filter state.", state, customers.filterState);
	
    }
    
    @Test
    public void testGetPagePrefix(){
	Assert.assertNotNull(getCustomersWithSize(10).getPageNumPrefix());
    }
    
    private Customers getCustomersWithSize(int n){
	List<CustomerListItem> list = new ArrayList<CustomerListItem>();
	for(int i = 0; i < 100; i++){
	    list.add(new CustomerListItem(new Customer(), null, 0));
	}
	return new Customers(list);
    }

}
