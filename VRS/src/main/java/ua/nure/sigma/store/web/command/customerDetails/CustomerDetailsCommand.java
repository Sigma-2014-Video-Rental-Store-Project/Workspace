package ua.nure.sigma.store.web.command.customerDetails;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim Sinkevich on 28.10.2014.
 */
public class CustomerDetailsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CustomerDetailsCommand.class);

    public static final String CUSTOMER_ID_PARAM_NAME = "customerId";
    public static final String CUSTOMER_PARAM_NAME = "customer";
    public static final String NOW_RENTS_PARAM_NAME = "nowRents";
    public static final String RENTED_HISTORY_PARAM_NAME = "rentedHistory";

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

        List<Rent> allRents = DAOFactory.getInstance().getRentDAO().findRentByCustomerID(custId);
        List<FilmForRent> nowRentsFilms = new ArrayList<FilmForRent>();
        List<FilmForRent> rentedHistoryFilms = new ArrayList<FilmForRent>();
        for (Rent r : allRents) {
            for (FilmForRent f : r.getFilmList()) {
                if (f.getAcceptedDate() == null) {
                    nowRentsFilms.add(f);
                } else {
                    rentedHistoryFilms.add(f);
                }
            }
        }

        request.setAttribute(NOW_RENTS_PARAM_NAME, nowRentsFilms);
        request.setAttribute(RENTED_HISTORY_PARAM_NAME, rentedHistoryFilms);
        request.setAttribute(CUSTOMER_PARAM_NAME, customer);
        return Paths.PAGE_CUSTOMER_DETAILS;
    }
}
