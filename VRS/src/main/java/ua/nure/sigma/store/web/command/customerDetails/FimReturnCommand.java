package ua.nure.sigma.store.web.command.customerDetails;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.logic.ListForCustomerDetails;
import ua.nure.sigma.store.states.CustomerDetailsNowRentState;
import ua.nure.sigma.store.states.CustomerDetailsRentHistoryState;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created 28.10.2014 by Vasiliy Skidanenko
 */
public class FimReturnCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FimReturnCommand.class);


    private static final String RETURN_TO_PAGE_PARAM_NAME = "returnTo";
    public static final String FILM_ID_PARAM_NAME = "filmId";
    public static final String RENT_ID_PARAM_NAME = "rentId";
    public static final String AMOUNT_OF_FILMS_PARAM_NAME = "amount";
    public static final String TOTAL_AMOUNT_OF_FILMS_PARAM_NAME = "copiesLeft";

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
        LOG.trace("return film command startred");

        String rentIdString = request.getParameter(RENT_ID_PARAM_NAME);
        String filmIdString = request.getParameter(FILM_ID_PARAM_NAME);
        String amountString = request.getParameter(AMOUNT_OF_FILMS_PARAM_NAME);
        String copiesLeftString = request.getParameter(TOTAL_AMOUNT_OF_FILMS_PARAM_NAME);

        int rentId;
        int filmId;
        int amount;
        int copiesLeft;

        try {
            filmId = Integer.parseInt(filmIdString);
            rentId = Integer.parseInt(rentIdString);
            amount = Integer.parseInt(amountString);
            copiesLeft = Integer.parseInt(copiesLeftString);
        } catch (Exception e) {
            LOG.error("Cannot parse 'filmId', 'amount', 'copiesLeftleft' or 'rentId' as Integer value.", e);

            return Paths.PAGE_NO_PAGE;
        }
        
        LOG.trace("rent id = "+rentId);
        LOG.trace("film id = "+filmId);
        LOG.trace("amount  = "+amount);
        LOG.trace("copiesLeft = "+copiesLeft);

        try{
        DAOFactory.getInstance().getFilmRentedDAO().updateFilmRent(rentId, filmId, amount);
        } catch (Exception e) {
            LOG.error("Something bad happens in DAO", e);
            return Paths.PAGE_NO_PAGE;
        }

        String returnTo = request.getParameter(RETURN_TO_PAGE_PARAM_NAME);
        LOG.trace("return film command ends");
        return returnTo;
    }
}
