package ua.nure.sigma.store.web.command.addNewAdmin;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.AdminDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.LocaleDAO;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Adds new Admin to the database and redirects on
 * Admin list page.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class CreateNewAdminCommand extends Command {

    public static final String ERR_MES_PARAM = "errMessage";

    private static final Logger LOG = Logger.getLogger(CreateNewAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command started.");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordRetype = request.getParameter("passwordRetype");
        String locale = request.getParameter("locale");

        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        Admin admin = adminDAO.findAdminByLogin(email);
        if (email == null || email.isEmpty()) {
            return Paths.COMMAND_ADD_NEW_ADMIN + "&" + ERR_MES_PARAM +
                    "=" + URLEncoder.encode("Specified email is empty.", "UTF-8");
        }
        if (admin != null) {
            return Paths.COMMAND_ADD_NEW_ADMIN + "&" + ERR_MES_PARAM +
                    "=" + URLEncoder.encode("Specified email is already in use.", "UTF-8");
        }
        if (password == null || password.isEmpty()) {
            return Paths.COMMAND_ADD_NEW_ADMIN + "&" + ERR_MES_PARAM +
                    "=" + URLEncoder.encode("Specified password must not be empty.", "UTF-8");
        }
        if (!password.equals(passwordRetype)) {
            return Paths.COMMAND_ADD_NEW_ADMIN + "&" + ERR_MES_PARAM +
                    "=" + URLEncoder.encode("Two passwords must match each other.", "UTF-8");
        }
        admin = new Admin();
        admin.setEmail(email);
        LOG.trace("Email to set: " + email);
        admin.setPassword(password.hashCode());
        LocaleDAO localeDAO = daoFactory.getLocaleDAO();
        int localeId = localeDAO.findLocaleIdByName(locale);
        admin.setLocale(localeId);
        LOG.trace("Locale to set: " + locale);
        admin.setRoleId(2);
        adminDAO.createAdmin(admin);
        LOG.debug("Command finished.");

        return Paths.COMMAND_ADMIN_LIST;
    }
}
