package ua.nure.sigma.store.web.command.customerDetails;

import org.junit.Test;
import ua.nure.sigma.store.dao.CustomerDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.CustomerDetails;
import ua.nure.sigma.store.logic.ListForCustomerDetails;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.addNewAdmin.CreateNewAdminCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

/**
 * Created by Maksim Sinkevich on 12.11.2014.
 */
public class CustomerDetailsCommandTest {
    @Test
    public void testExecute() throws IOException, ServletException {
        HttpSession session = mock(HttpSession.class);
        List<CustomerDetails> listForCustomerDetails = new ArrayList<CustomerDetails>();

        Customer customer = new Customer();
        customer.setCustomerID(1);

        CustomerDAO customerDAO = mock(CustomerDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCustomerDAO()).thenReturn(customerDAO);


        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter("customerId")).thenReturn("1");
        when(requestMock.getParameter("filter")).thenReturn("");
        when(requestMock.getParameter("pageIndex")).thenReturn("");
        when(requestMock.getParameter("sorting")).thenReturn("");
        when(requestMock.getParameter("direct")).thenReturn("");
        when(requestMock.getSession()).thenReturn(session);
        when(requestMock.getSession().getAttribute("customerDetailsList")).thenReturn(listForCustomerDetails);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        CustomerDetailsCommand command = new CustomerDetailsCommand();
        String result = command.execute(requestMock, responseMock);

        assertEquals(Paths.PAGE_NO_PAGE, result);
    }
}
