package ua.nure.sigma.store.web.command.customerlist;

import org.apache.log4j.Logger;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Vasiliy on 20.10.14.
 */

public class CustomerListSearchCommand extends Command {
    private static final String SEARCH_PARAM_NAME = "key";
    private static final Logger LOG = Logger.getLogger(CustomerListSearchCommand.class);

    public static String decodeGetParameter(String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("ISO-8859-1"),"UTF8");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        String searchName = (String) request.getParameter(SEARCH_PARAM_NAME);

        LOG.debug("Search command started.");

        if (searchName != null) {
            searchName = decodeGetParameter((String) request.getParameter(SEARCH_PARAM_NAME));
            LOG.debug("Search starts with arg = " + searchName + ".");
            
            List<CustomerListItem> customers = ((Customers) request.getSession().getAttribute(CustomerListCommand.CUSTOMERS_PARAM_NAME)).getAllItems();
                
            List<CustomerListItem> foundCustomer = new ArrayList<CustomerListItem>();
            for (int i=0;i<customers.size();++i){
                if ((customers.get(i).getFirstName()+" "+customers.get(i).getLastName()).toLowerCase().contains(searchName.toLowerCase()))
                    foundCustomer.add(customers.get(i));
            }
            LOG.debug(foundCustomer.size());
            request.getSession().setAttribute(CustomerListCommand.CUSTOMERS_PARAM_NAME, new Customers(foundCustomer));

        }
        LOG.debug("Search command finished");
        return null;
    }
}
