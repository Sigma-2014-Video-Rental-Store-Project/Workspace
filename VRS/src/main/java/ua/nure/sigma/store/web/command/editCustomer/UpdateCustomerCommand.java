package ua.nure.sigma.store.web.command.editCustomer;

import ua.nure.sigma.store.validator.Validator;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vlad on 27.10.14.
 */
public class UpdateCustomerCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return "error";
    }

    public UpdateCustomerCommand(List<String> imageExtensions, Validator validator) {
    }
}
