package ua.nure.sigma.store.web.command.adminList;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.AdminDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

/**
 * This command must be processed by GET method call.
 * Prepares list of administrator accounts to represent them
 * in readable form to the super root.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class AdminListCommand extends Command {

    public static final String ADMINS_ATTRIBUTE_NAME = "admins";
    public static final String ADMIN_EMAIL_PARAM_NAME = "adminEmail";

    private static final Logger LOG = Logger.getLogger(AdminListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LOG.trace("Command started.");
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        List<Admin> admins = adminDAO.findAllAdmins();

        // If search engine is applied...
        String searchParam = request.getParameter(ADMIN_EMAIL_PARAM_NAME);
        LOG.trace("Search parameter: " + searchParam);
        if (searchParam != null && !searchParam.isEmpty()) {
            ListIterator<Admin> iterator = admins.listIterator();
            while (iterator.hasNext()) {
                if (!iterator.next().getEmail().equals(searchParam)) {
                    iterator.remove();
                }
            }
        }
        request.setAttribute(ADMINS_ATTRIBUTE_NAME, admins);
        LOG.trace("Attribute to set: " + ADMINS_ATTRIBUTE_NAME + " :: " + admins);
        LOG.trace("Command finished.");

        return Paths.PAGE_ADMIN_LIST;
    }
}
