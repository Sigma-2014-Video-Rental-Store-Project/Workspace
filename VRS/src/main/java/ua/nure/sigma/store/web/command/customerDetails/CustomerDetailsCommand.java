package ua.nure.sigma.store.web.command.customerDetails;

import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maksim Sinkevich on 28.10.2014.
 */
public class CustomerDetailsCommand extends Command {
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

        return Paths.PAGE_CUSTOMER_DETAILS;
    }
}
