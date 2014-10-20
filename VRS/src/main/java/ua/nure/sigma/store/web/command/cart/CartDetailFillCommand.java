package ua.nure.sigma.store.web.command.cart;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Customers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Maksim Sinkevich on 20.10.2014.
 */
public class CartDetailFillCommand extends Command {
    public static final String CUSTOMERS_PARAM_NAME = "customers";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Customer> customers = DAOFactory.getInstance().getCustomerDAO().findAllCustomers();
        Customers customersParam = new Customers(customers);
        request.setAttribute(CUSTOMERS_PARAM_NAME, customersParam);
        return Paths.PAGE_CART_DETAIL_FILMS;
    }
}
