package ua.nure.sigma.store.web.command.customerlist;

import static org.easymock.EasyMock.expect;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmRentedDAO;
import ua.nure.sigma.store.dao.RentDAO;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class CustomerToCustomerListItemConverterTest {

    /**
     * Numer of miliseconds in the day
     */
    private static final long DAY = 86400000;
    
    @Test
    public void testConvertFromCustomersToCustomerListItems() throws Exception {
	List<Customer> customers = new ArrayList<Customer>();
	Customer customer = new Customer();
	customer.setCustomerID(1);
	customers.add(customer);
	
	List<Rent> rents = new ArrayList<Rent>();
	Rent rent = new Rent();
	rent.setCustomerID(customer.getCustomerID());
	rent.setRentID(2);
	rents.add(rent);
	
	// film for rents: +1 day, +2 days
	List<FilmForRent> filmForRents = new ArrayList<FilmForRent>();
	FilmForRent filmForRent = new FilmForRent();
	filmForRent.setRentID((int)rent.getRentID());
	Date minDate = new Date(new Date().getTime() + DAY);
	filmForRent.setFutureDate(minDate);
	FilmForRent filmForRent1 = new FilmForRent();
	filmForRent1.setRentID((int)rent.getRentID());
	Date date1 = new Date(new Date().getTime() + DAY * 2);
	filmForRent1.setFutureDate(date1);
	filmForRents.add(filmForRent);
	filmForRents.add(filmForRent1);
	
	RentDAO rentDAOMock = mock(RentDAO.class);
	FilmRentedDAO filmRentedDAOMock = mock(FilmRentedDAO.class);
	
	DAOFactory daoFactoryMock = mock(DAOFactory.class);
	when(daoFactoryMock.getRentDAO()).thenReturn(rentDAOMock);
	when(daoFactoryMock.getFilmRentedDAO()).thenReturn(filmRentedDAOMock);
	
	mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactoryMock).anyTimes();
        replay(DAOFactory.class);
        replay(DAOFactory.class);
        
        when(rentDAOMock.findRentByCustomerID(customer.getCustomerID())).thenReturn(rents);
        when(filmRentedDAOMock.findFilmRentedByRentID(rent.getRentID())).thenReturn(filmForRents);

        List<CustomerListItem> res = new ArrayList<CustomerListItem>();
        CustomerToCustomerListItemConverter.convertFromCustomersToCustomerListItems(customers, res);

        Assert.assertEquals(customers.size(), res.size());
        Assert.assertEquals(minDate, res.get(0).getReturnDate());
        Assert.assertEquals(2, res.get(0).getCopiesRented());
    }
    
    @Test
    public void testConst(){
	new CustomerToCustomerListItemConverter();
    }

}
