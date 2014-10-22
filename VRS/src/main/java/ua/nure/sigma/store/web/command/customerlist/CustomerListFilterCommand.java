package ua.nure.sigma.store.web.command.customerlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.states.CustomerFilterAllState;
import ua.nure.sigma.store.states.CustomerFilterWithFilmsState;
import ua.nure.sigma.store.states.IListFilterState;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Customers;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vasiliy Skidanenko on 20.10.14.
 */
public class CustomerListFilterCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CustomerListFilterCommand.class);
    private static Map<String, IListFilterState<CustomerListItem>> filterStateMap;

    static {
        filterStateMap = new HashMap<String, IListFilterState<CustomerListItem>>();
        filterStateMap.put("all", new CustomerFilterAllState());
        filterStateMap.put("withFilms", new CustomerFilterWithFilmsState());
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filterParam = request.getParameter(CustomerListCommand.FILTER_PARAM_NAME);

        LOG.debug("CustomerListFilterCommand started with param = " + filterParam);

        if (filterParam != null && !filterParam.isEmpty()) {
            Customers customers = (Customers) request.getSession().getAttribute(CustomerListCommand.CUSTOMERS_PARAM_NAME);
            IListFilterState<CustomerListItem> state = filterStateMap.get(filterParam);
            if(state != null){
                customers.setFilterState(state);
                LOG.debug("Filtering succeed.");
            }
        }

        LOG.debug("FilmListCommand finished.");
        return null;
    }
}
