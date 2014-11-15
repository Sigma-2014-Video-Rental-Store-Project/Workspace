package ua.nure.sigma.store.web.command.customerDetails;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.comparators.CustomerDetailsComparatorFactory;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.CustomerDetails;
import ua.nure.sigma.store.logic.ListForCustomerDetails;
import ua.nure.sigma.store.states.CustomerDetailsNowRentState;
import ua.nure.sigma.store.states.CustomerDetailsRentHistoryState;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Maksim Sinkevich on 28.10.2014.
 */
public class CustomerDetailsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CustomerDetailsCommand.class);

    public static final String CUSTOMER_ID_PARAM_NAME = "customerId";
    public static final String FILTER_PARAM_NAME = "filter";
    static final String PAGE_PARAM_NAME = "pageIndex";
    public static final String CUSTOMER_DETAILS_LIST_PARAM_NAME = "customerDetailsList";
    static final String SORT_PARAM_NAME = "sorting";
    static final String DIRECT_PARAM_NAME = "direct";
    private static final String UP_DIR = "up";
    private static final String DOWN_DIR = "down";

    @Override
    /**
     * This command fills list of orders for customer details list page.
     *
     * @param request request.
     * @param response response.
     * @return redirection.
     * @throws IOException
     * @throws ServletException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String custIdString = request.getParameter(CUSTOMER_ID_PARAM_NAME);
        int custId;

        try {
            custId = Integer.parseInt(custIdString);
        } catch (Exception e) {
            LOG.error("Cannot parse 'customerId' as Integer value.", e);

            return Paths.PAGE_NO_PAGE;
        }

        Customer customer = DAOFactory.getInstance().getCustomerDAO().findCustomerByID(custId);
        if (customer == null) {
            LOG.error("Cannot find customer by id: " + custId);

            return Paths.PAGE_NO_PAGE;
        }

        ListForCustomerDetails listForCustomerDetails = new ListForCustomerDetails(customer);
        request.setAttribute(CUSTOMER_DETAILS_LIST_PARAM_NAME, listForCustomerDetails);
        filter(request, response, listForCustomerDetails);
        sort(request, response, listForCustomerDetails);
        pages(request, response, listForCustomerDetails);
        request.setAttribute(CUSTOMER_DETAILS_LIST_PARAM_NAME, listForCustomerDetails);
        return Paths.PAGE_CUSTOMER_DETAILS;
    }

    private void pages(HttpServletRequest request, HttpServletResponse response, ListForCustomerDetails listForCustomerDetails) {
        String pageString = (String) request.getParameter(PAGE_PARAM_NAME);
        if (pageString != null && !pageString.equals("")) {
            listForCustomerDetails = (ListForCustomerDetails) request.getAttribute(CUSTOMER_DETAILS_LIST_PARAM_NAME);
            LOG.trace("Selected page=" + pageString);
            listForCustomerDetails.setPageIndex(Integer.valueOf(pageString));
        }
    }

    private void filter(HttpServletRequest request, HttpServletResponse response, ListForCustomerDetails listForCustomerDetails) {
        String filter = request.getParameter(FILTER_PARAM_NAME);
        LOG.trace("Start filtering customrtDetails with filter=" + filter);
        if (filter == null || filter.equals("") || filter.equals("nowRent")) {
            request.getSession().setAttribute(FILTER_PARAM_NAME,"nothistory");
            listForCustomerDetails.setFilterState(new CustomerDetailsNowRentState());
        } else {
            request.getSession().setAttribute(FILTER_PARAM_NAME,"history");
            listForCustomerDetails.setFilterState(new CustomerDetailsRentHistoryState());
        }
        LOG.trace("Finish filtering customerDetails.");
    }

    private void sort(HttpServletRequest request, HttpServletResponse response, ListForCustomerDetails listForCustomerDetails) {
        String sortName = request.getParameter(SORT_PARAM_NAME);
        String direct = request.getParameter(DIRECT_PARAM_NAME);
        LOG.trace("Sorting started");
        if (sortName != null && direct != null) {
            LOG.trace("Sortname=" + sortName + "; direct=" + direct + ".");
            List<CustomerDetails> customerDetailses = listForCustomerDetails.getModel();
            Comparator<CustomerDetails> comparator = CustomerDetailsComparatorFactory.getComparator(sortName);
            if (comparator == null) {
                return;
            }

            Collections.sort(customerDetailses, comparator);

            if (direct.equals(DOWN_DIR)) {
                Collections.sort(customerDetailses, Collections.reverseOrder(comparator));
            }
            listForCustomerDetails.setCustomerDetailsList(customerDetailses);
        }
        LOG.trace("Sorting finished");
    }
}
