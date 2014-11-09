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
import java.net.URLEncoder;

/**
 * This command must be processed by POST method call.
 * Changes password of the specified admin account if
 * both password and retyped passwords are verified and
 * do match each other.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class ChangeAdminPasswordCommand extends Command {

    public static final String MESSAGE_ATTRIBUTE_NAME = "messageCAPC";
    public static final String ADMIN_ID_PARAM_NAME = "adminId";
    public static final String ADMIN_PASSWORD_PARAM_NAME = "password";
    public static final String ADMIN_PASSWORD_RETYPE_PARAM_NAME = "passwordRetype";

    private static final Logger LOG = Logger.getLogger(ChangeAdminPasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Command started.");
        String returnTo = Paths.COMMAND_ADMIN_LIST;
        String adminIdString = request.getParameter(ADMIN_ID_PARAM_NAME);
        int adminId = Integer.parseInt(adminIdString);
        String password = request.getParameter(ADMIN_PASSWORD_PARAM_NAME);
        String passwordRetype = request.getParameter(ADMIN_PASSWORD_RETYPE_PARAM_NAME);
        LOG.trace("Id parameter: " + adminIdString);
        LOG.trace("Password parameter: " + password);
        LOG.trace("Retyped password parameter: " + passwordRetype);
        String errorMessage = validatePassword(password, passwordRetype);
        LOG.trace("Error message to set: " + errorMessage);
        if (errorMessage != null) {
            returnTo += "&" + MESSAGE_ATTRIBUTE_NAME + "=" + URLEncoder.encode(errorMessage, "UTF-8");
        } else {
            DAOFactory daoFactory = DAOFactory.getInstance();
            AdminDAO adminDAO = daoFactory.getAdminDAO();
            Admin admin = adminDAO.findAdminById(adminId);
            admin.setPassword(password.hashCode());
            adminDAO.updateAdminPassword(admin);
            returnTo += "&" + MESSAGE_ATTRIBUTE_NAME + "=" + URLEncoder.encode(admin.getEmail() + " password has been changed.", "UTF-8");
            LOG.debug("Admin password has been changed.");
        }
        LOG.trace("Command finished.");

        return returnTo;
    }

    private String validatePassword(String password, String retype) {
        if (password == null || password.isEmpty()) {
            return "Empty password field";
        }
        if (!password.equals(retype)) {
            return "Passwords do not match each other.";
        }

        return null;
    }
}
