package ua.nure.sigma.store.web.command.editCustomer;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.command.editfilm.EditFilmCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 27.10.14.
 */
public class DeleteCustomerCommand extends Command {
    private static final Logger LOG = Logger.getLogger(DeleteCustomerCommand.class);

    /**
     * Deletes {@code Customer} object by the specified id from the current database.
     * Processes {@code null} id and unparsable id exceptions.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("DeleteCustomerCommand started.");
        String customerIdString = request.getParameter(EditCustomerCommand.CUSTOMER_ID_PARAM_NAME);

        // If this command has been called plain - return 'no page' handler.
        if (customerIdString == null) {
            LOG.warn("Cannot get 'customerId' parameter.");

            return Paths.PAGE_NO_PAGE;
        }
        int customerId;
        try {

            // Notice, that if this parameter cannot be parsed properly,
            // NumberFormatException will be thrown.
            customerId = Integer.parseInt(customerIdString);
        } catch (NumberFormatException e) {
            LOG.error("Cannot parse 'customerId' as Integer value.", e);

            return Paths.PAGE_NO_PAGE;
        }

        // Tries to remove customer with specified id.
        DAOFactory.getInstance().getCustomerDAO().deleteCustomerByID(customerId);
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Next customer with id equals to '%d' has been deleted from the database.",
                    customerId));
            LOG.debug("DeleteCustomerCommand finished.");
        }

        return Paths.PAGE_CUSTOMER_LIST;
    }
}
