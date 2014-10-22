package ua.nure.sigma.store.web.command.addNewCustomer;

import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 22.10.14.
 */

// this command invoke addNewFilm.jsp
public class AddNewCustomerCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return Paths.PAGE_ADD_NEW_CUSTOMER;
    }
}
