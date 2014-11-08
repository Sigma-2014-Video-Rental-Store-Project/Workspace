package ua.nure.sigma.store.web.command.addNewAdmin;

import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forwards on Add new Admin page.
 * Must be processed by GET method.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class AddNewAdminCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return Paths.PAGE_ADD_NEW_ADMIN;
    }
}
