package ua.nure.sigma.store.web.command.cart;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.*;
import ua.nure.sigma.store.logic.Cart;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Cart cart = (Cart) request.getAttribute(ORDER_DETAIL_OBJECT);
        OrderDetailsView view = mapToOrderDetailsView(currentRent,cart);
        request.setAttribute(ORDER_DETAIL_OBJECT, view);
        return Paths.PAGE_VIEW_ORDER_DETAIL;
    }
    private OrderDetailsView mapToOrderDetailsView(Rent rent, Cart cart){
        return new OrderDetailsView(
                rent.getRentID(),
                mapCustomerName(cart.getCurrentCustomer()),
                rent.getRentDate(),
                mapToRentedFilmView(cart.getContent()),
                cart.getBonusForRent(),
                cart.getTotalCost(),
                cart.getTotalCost()-cart.getBonusForRent()
        );
    }

    private String mapCustomerName(Customer customer){
        return customer.getLastName()+" "
                + customer.getFirstName().substring(0,1).toUpperCase()
                +"."
                +customer.getMiddleName().substring(0,1)
                +".";
    }
    private List<RentedFilmView> mapToRentedFilmView(Map<Film,FilmForRent> rentMap){
        List<RentedFilmView> filmViews = new ArrayList<RentedFilmView>();
        for (Map.Entry<Film,FilmForRent> entry : rentMap.entrySet()){
            filmViews.add(
                    new RentedFilmView(
                            entry.getKey().getTitle(),
                            entry.getValue().getCopies(),
                            entry.getValue().getFutureDate(),
                            entry.getKey().getRentPrice()
                            ));
        }
        return filmViews;
    }

//    private OrderDetailsView mapRentToOrderDetailsView(Rent rent) {
//        List<RentedFilmView> filmList = mapFilmForRentToRentedFilmView(rent.getFilmList());
//        Customer customer = DAOFactory.getInstance().getCustomerDAO().findCustomerByID(rent.getCustomerID());
//        return map(rent,filmList, customer);
//    }

//    private OrderDetailsView map(Rent rent, List<RentedFilmView> films, Customer customer){
//        ///TODO: mapper
//        return new OrderDetailsView(0, null, null, null, 0, 0, 0);
//    }
//    private List<RentedFilmView> mapFilmForRentToRentedFilmView(List<FilmForRent> forRent){
//        FilmDAO filmDAO = DAOFactory.getInstance().getFilmDAO();
//        List<RentedFilmView> filmList = new ArrayList<RentedFilmView>();
//        for (FilmForRent filmForRent : forRent){
//            Film film = filmDAO.findFilmById(filmForRent.getFilmID());
//            filmList.add(mapToRentedFilmView(film, filmForRent));
//        }
//        return filmList;
//    }
//    private RentedFilmView mapToRentedFilmView(Film film, FilmForRent forRent){
//
//        return new RentedFilmView(
//                        film.getTitle(),
//                        forRent.getCopies(),
//                        forRent.getFutureDate(),
//                        film.getRentPrice()
//        );
//    }
}

