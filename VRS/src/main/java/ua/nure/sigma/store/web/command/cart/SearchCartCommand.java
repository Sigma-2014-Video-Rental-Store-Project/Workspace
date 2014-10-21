package ua.nure.sigma.store.web.command.cart;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

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

    public static final String CUSTOMER_ID_PARAM_NAME = "customerId";
    public static final String RENT_ID_PARAM_NAME = "rents";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String customerIdString = request.getParameter(CUSTOMER_ID_PARAM_NAME);
        int customerId;
        try {
            customerId = Integer.parseInt(customerIdString);
        } catch (NumberFormatException e) {
            LOG.error("Cannot parse 'filmId' as Integer value.", e);

            return Paths.PAGE_NO_PAGE;
        }
        List<Rent> rentList = DAOFactory.getInstance().getRentDAO().findRentByCustomerID(customerId);
        request.setAttribute(RENT_ID_PARAM_NAME, rentList);
        return Paths.PAGE_CART_DETAIL_FILMS;
    }
}
