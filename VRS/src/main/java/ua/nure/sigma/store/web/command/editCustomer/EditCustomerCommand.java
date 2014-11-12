package ua.nure.sigma.store.web.command.editCustomer;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.entity.Sex;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Categories;
import ua.nure.sigma.store.web.list.Sexes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vlad on 27.10.14.
 */
public class EditCustomerCommand extends Command {

    public static final String CUSTOMER_ID_PARAM_NAME = "customerId";
    private  static final String USER_PARAM_NAME = "user";
    private static final String EDIT_CUSTOMER_OBJECT = "editCustomerObject";

    private static final Logger LOG = Logger.getLogger(EditCustomerCommand.class);

    /**
     * Processes request for the {@literal Edit Form} representation. Must be supplied
     * with {@code customerId} parameter through the {@code request}. Notice, that if there
     * is no {@code customerId} parameter at all or it cannot be parsed properly, this
     * command will redirect request on standard no page handler.
     */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("EditCustomerCommand started.");

        // Must be specified with this command.
        String customerIdString = request.getParameter(CUSTOMER_ID_PARAM_NAME);

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
        Customer customerToEdit = DAOFactory.getInstance().getCustomerDAO().findCustomerByID(customerId);
        // If the current database does not contain film with specified ID -
        // return 'no page' handler.
        if (customerToEdit == null) {
            LOG.warn(String.format("Cannot find film with id equals to %d in the database.", customerId));
            return Paths.PAGE_NO_PAGE;
        }
        Admin admin = (Admin) request.getSession().getAttribute(USER_PARAM_NAME);
        Sex customerSex =  DAOFactory.getInstance().getSexDAO().findSexByID(customerToEdit.getSexID(),admin.getLocale());
        if(customerSex != null){
        	request.setAttribute("sex", customerSex);
        }
        List<Sex> sexes = DAOFactory.getInstance().getSexDAO().findAllSex(admin.getLocale());
        Sexes paramSexes = new Sexes(sexes);
        for (Sex sex : sexes) {
            LOG.debug(sex.getSexName());
        }
        request.setAttribute("sexes", paramSexes);
        request.setAttribute(CUSTOMER_ID_PARAM_NAME, customerIdString);
        request.setAttribute(EDIT_CUSTOMER_OBJECT, customerToEdit);
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Next film was set as '%s' attribute in the request scope: %s",
                    EDIT_CUSTOMER_OBJECT, customerToEdit));
            LOG.debug("EditCustomerCommand finished.");
        }

        return Paths.PAGE_EDIT_CUSTOMER;
    }
}
