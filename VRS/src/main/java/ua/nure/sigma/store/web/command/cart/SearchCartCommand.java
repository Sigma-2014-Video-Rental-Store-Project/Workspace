package ua.nure.sigma.store.web.command.cart;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.command.filmlist.FilmListSearchCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Maksim Sinkevich on 21.10.2014.
 */
public class SearchCartCommand extends Command {
    private static final Logger LOG = Logger.getLogger(SearchCartCommand.class);

    public static final String CUSTOMER_FULLNAME_PARAM_NAME = "customerFullName";
    public static final String CART_RENT_PARAM_NAME = "cartRent";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String customerName = request.getParameter(CUSTOMER_FULLNAME_PARAM_NAME);
        Rent cartRent = (Rent) request.getSession().getAttribute(CART_RENT_PARAM_NAME);
        String[] customerNames = customerName.split(" ");
        if (customerNames.length != 2) {
            return Paths.PAGE_NO_PAGE;
        }
        List<Customer> customerList = DAOFactory.getInstance().getCustomerDAO().findAllCustomers();
        int customerId = -1;
        for (Customer c : customerList) {
            if (c.getFirstName().equals(customerNames[0]) && c.getLastName().equals(customerNames[1])) {
                customerId = c.getCustomerID();
            }
        }

        if (customerId != -1) {
            cartRent.setCustomerID(customerId);
        }
        customerName = FilmListSearchCommand.decodeGetParameter(customerName);
        request.getSession().setAttribute(CART_RENT_PARAM_NAME, cartRent);
        request.setAttribute(CUSTOMER_FULLNAME_PARAM_NAME, customerName);
        return Paths.PAGE_CART_DETAIL_FILMS;
    }
}
