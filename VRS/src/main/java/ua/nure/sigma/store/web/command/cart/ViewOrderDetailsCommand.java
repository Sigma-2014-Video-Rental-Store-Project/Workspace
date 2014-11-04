package ua.nure.sigma.store.web.command.cart;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.entity.OrderDetailsView;
import ua.nure.sigma.store.entity.Rent;
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
    public static final String RENT_OBJECT = "current_rent";

    private static final String ORDER_DETAIL_OBJECT = "rent";

    private static final Logger LOG = Logger.getLogger(ViewOrderDetailsCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ///TODO: parse from session parameter
        LOG.debug("ViewOrderDetailsCommand started.");
        Rent currentRent = (Rent) request.getAttribute(RENT_OBJECT);
        OrderDetailsView view = mapRentToOrderDetailsView(currentRent);
        request.setAttribute(ORDER_DETAIL_OBJECT, view);
        return Paths.PAGE_VIEW_ORDER_DETAIL;
    }

    private OrderDetailsView mapRentToOrderDetailsView(Rent rent){
        ///TODO: mapper
        return new OrderDetailsView(0,null,null,null,0,0,0);
    }
}
