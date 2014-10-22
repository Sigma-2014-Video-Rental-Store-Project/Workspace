package ua.nure.sigma.store.web.command.customerlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.command.filmlist.IComplexCommand;
import ua.nure.sigma.store.web.list.Customers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 20.10.14.
 */
public class CustomerListCommand extends Command implements IComplexCommand {

    public static final String FILTER_PARAM_NAME = "filter";
    public static final String SORT_PARAM_NAME = "sort";
    public static final String DIRECT_PARAM_NAME = "direct";
    private List<Command> commands;

    private static final Logger LOG = Logger.getLogger(CustomerListCommand.class);

    static final String CUSTOMERS_PARAM_NAME = "customers";
    static final String PAGE_PARAM_NAME = "pageIndex";

    CustomerListCommand() {
        commands = new ArrayList<Command>();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("CustomerListCommand starts.");
        checkAndCreateCustomerList(request, response);
        notifyAllCommands(request, response);
        LOG.debug("CustomerListCommand finishes.");
        return Paths.PAGE_CUSTOMER_LIST;
    }

    private void checkAndCreateCustomerList(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute(CUSTOMERS_PARAM_NAME) == null) {
            LOG.debug("Creating list of customers.");
            DAOFactory df = DAOFactory.getInstance();
            List<Customer> customers = df.getCustomerDAO().findAllCustomers();
            Customers paramFilms = new Customers(customers);
            request.getSession().setAttribute(CUSTOMERS_PARAM_NAME, paramFilms);
        }
    }

    private void notifyAllCommands(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        for (Command command : commands) {
            command.execute(request, response);
        }
    }

    @Override
    public void addCommandListener(Command command) {
        commands.add(command);
    }
}
