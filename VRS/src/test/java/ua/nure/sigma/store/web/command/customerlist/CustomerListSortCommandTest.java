package ua.nure.sigma.store.web.command.customerlist;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mock;

import ua.nure.sigma.store.comparators.CustomerBonusComparator;
import ua.nure.sigma.store.comparators.CustomerComparatorFactory;
import ua.nure.sigma.store.comparators.FilmComparatorFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.list.Customers;
import ua.nure.sigma.store.web.list.Films;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;
import static org.easymock.EasyMock.expect;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.command.filmlist.FilmListCommand;
import ua.nure.sigma.store.web.command.filmlist.FilmListSortCommand;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)public class CustomerListSortCommandTest {

    @Test
    public void testExecute() throws Exception {
	List<CustomerListItem> list = new ArrayList<CustomerListItem>();
	Customer customer1 = new Customer();
	customer1.addBonus(1);
	Customer customer2 = new Customer();
	customer2.addBonus(5);
	Customer customer3 = new Customer();
	customer3.addBonus(2);
	list.add(new CustomerListItem(customer1, new Date(), 0));
	list.add(new CustomerListItem(customer2, new Date(), 0));
	list.add(new CustomerListItem(customer3, new Date(), 0));
	Customers customers = new Customers(list);
	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	
	when(requestMock.getParameter(CustomerListCommand.SORT_PARAM_NAME)).thenReturn(CustomerComparatorFactory.CUSTOMER_BONUS_COMPARATOR);
	when(requestMock.getParameter(CustomerListCommand.DIRECT_PARAM_NAME)).thenReturn(CustomerListSortCommand.DOWN_DIR_NAME);
	when(requestMock.getSession()).thenReturn(sessionMock);
	
	when(sessionMock.getAttribute(CustomerListCommand.CUSTOMERS_PARAM_NAME)).thenReturn(customers);
	
	CustomerListSortCommand command = new CustomerListSortCommand();
	command.execute(requestMock, responseMock);
	
	for(int i = 0; i < list.size() - 1; i++){
	    CustomerListItem curCustomer = list.get(i);
	    CustomerListItem nextCustomer = list.get(i + 1);
	    assertTrue(new CustomerBonusComparator().compare(curCustomer, nextCustomer) >= 0);
	}
    }

}
