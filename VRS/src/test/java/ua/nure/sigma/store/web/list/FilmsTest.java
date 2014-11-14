package ua.nure.sigma.store.web.list;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.states.CustomerFilterAllState;
import ua.nure.sigma.store.states.FilmListFilterAvailableState;
import ua.nure.sigma.store.states.IListFilterState;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

public class FilmsTest {

    @Test
    public void testCustomers() throws Exception {
	new Customers(new ArrayList<CustomerListItem>());
    }

    @Test
    public void testGetModel() throws Exception {
	Films films = getFilmsWithSize(100);
	List<Film> model = films.getModel();
	Assert.assertEquals("Model doesn't contain 10 elements", 10, model.size());
    }

    @Test
    public void testGetAllItems() throws Exception {
	Films films = getFilmsWithSize(100);
	List<Film> allItems = films.getAllFilms();
	Assert.assertEquals("Method AllElements() doesn't contain all elements", 100, allItems.size());
    }

    @Test
    public void testGetPager() throws Exception {
	Films films = getFilmsWithSize(100);
	Assert.assertNotNull("Films return null pager.", films.getPager());
    }

    @Test
    public void testSetPageIndex() throws Exception {
	Films films = getFilmsWithSize(100);
	films.setPageIndex(5);
	Assert.assertEquals("SetPageIndex doesn't work.", 5, films.getPager().getPageIndex());
    }

    @Test
    public void testSetFilterState() throws Exception {
	Films films = getFilmsWithSize(10);
	IListFilterState<Film> state = new FilmListFilterAvailableState();
	films.setFilterState(state);
    }
    
    @Test
    public void testGetPagePrefix(){
	Assert.assertNotNull(getFilmsWithSize(10).getPageNumPrefix());
    }
    
    private Films getFilmsWithSize(int n) {
	List<Film> list = new ArrayList<Film>();
	for(int i = 0; i < n; i++){
	    list.add(new Film());
	}
	return new Films(list);
    }

}
