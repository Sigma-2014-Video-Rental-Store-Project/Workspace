package ua.nure.sigma.store.web.command.customerlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Customers;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Laposhko on 22.10.14.
 */
public class CustomerListFillAllCustomersCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CustomerListFillAllCustomersCommand.class);

    /**
     * Fills session param with list of customers. Does nothing if session object already exists.
     * @param request request.
     * @param response response.
     * @return null!
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //if (request.getSession().getAttribute(CustomerListCommand.CUSTOMERS_PARAM_NAME) == null) {
            LOG.debug("Filling session by all customers. Paramname = " + CustomerListCommand.CUSTOMERS_PARAM_NAME);
            DAOFactory df = DAOFactory.getInstance();
            List<Customer> customers = df.getCustomerDAO().findAllCustomers();
            List<CustomerListItem> listItems = new ArrayList<CustomerListItem>();

            CustomerToCustomerListItemConverter.convertFromCustomersToCustomerListItems(customers, listItems);

            Customers paramCustomers = new Customers(listItems);
            request.getSession().setAttribute(CustomerListCommand.CUSTOMERS_PARAM_NAME, paramCustomers);
        //}
        return null;
    }

}
