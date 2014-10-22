package ua.nure.sigma.store.web.command.customerlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmRentedDAO;
import ua.nure.sigma.store.dao.RentDAO;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.command.filmlist.IComplexCommand;
import ua.nure.sigma.store.web.list.Customers;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergey Laposhko on 20.10.14.
 */
public class CustomerListCommand extends Command implements IComplexCommand {

    public static final String FILTER_PARAM_NAME = "filter";
    public static final String SORT_PARAM_NAME = "sorting";
    public static final String DIRECT_PARAM_NAME = "direct";
    public static final String CUSTOMERS_PARAM_NAME = "customers";
    static final String PAGE_PARAM_NAME = "pageIndex";

    private List<Command> commands;

    private static final Logger LOG = Logger.getLogger(CustomerListCommand.class);


    CustomerListCommand() {
        commands = new ArrayList<Command>();
    }

    /**
     * This command fills customers object for customer list page. Filling object is <code>CustomerListItem</code>.
     *
     * @param request request.
     * @param response response.
     * @return redirection.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("CustomerListCommand starts.");
        notifyAllCommands(request, response);
        LOG.debug("CustomerListCommand finishes.");
        return Paths.PAGE_CUSTOMER_LIST;
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
