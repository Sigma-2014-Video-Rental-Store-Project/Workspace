package ua.nure.sigma.store.web.command.customerlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.comparators.CustomerComparatorFactory;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Customers;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Sergey Laposhko on 20.10.14.
 */
public class CustomerListSortCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CustomerListSortCommand.class);
    static final String UP_DIR_NAME = "up";
    static final String DOWN_DIR_NAME = "down";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sortName = (String) request.getParameter(CustomerListCommand.SORT_PARAM_NAME);
        String direct = (String) request.getParameter(CustomerListCommand.DIRECT_PARAM_NAME);
        LOG.debug("Sorting command started.");
        if (sortName != null && direct != null) {
            LOG.debug("Sorting started with sortname = " + sortName + "; direct = " + direct + ".");

            List<CustomerListItem> items = ((Customers) request.getSession().getAttribute(CustomerListCommand.CUSTOMERS_PARAM_NAME)).getAllItems();
            Comparator<CustomerListItem> comparator = CustomerComparatorFactory.getComparator(sortName);
            if (comparator == null)
                return null;

            Collections.sort(items, comparator);
            if (direct.equals(DOWN_DIR_NAME))
                Collections.sort(items, Collections.reverseOrder(comparator));

            LOG.debug("Sorting finished");
        }
        LOG.debug("Sorting command finished");
        return null;
    }
}
