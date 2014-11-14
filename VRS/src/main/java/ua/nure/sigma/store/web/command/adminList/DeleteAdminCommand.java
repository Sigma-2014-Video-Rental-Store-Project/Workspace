package ua.nure.sigma.store.web.command.adminList;

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
 * Deletes admin account permanently. Cannot be applied
 * to the root account.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class DeleteAdminCommand extends Command {

    public static final String ADMIN_ID_PARAM_NAME = "adminId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String adminIdString = request.getParameter(ADMIN_ID_PARAM_NAME);
        int adminId = Integer.parseInt(adminIdString);
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        Admin admin = adminDAO.findAdminById(adminId);
        adminDAO.deleteAdmin(adminDAO.findAdminById(adminId));

        return Paths.COMMAND_ADMIN_LIST + "&" +
                ChangeAdminPasswordCommand.MESSAGE_ATTRIBUTE_NAME +
                "=" + URLEncoder.encode("Admin with email = " +
                admin.getEmail() + " has been deleted.", "UTF-8");
    }
}
