package ua.nure.sigma.store.web.command.cart;

import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 04.11.14.
 */
public class ViewOrderDetailsCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ///TODO: parse from session parameter
        return Paths.PAGE_VIEW_ORDER_DETAIL;
    }
}
