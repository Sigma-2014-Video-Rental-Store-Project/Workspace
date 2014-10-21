package ua.nure.sigma.store.web.command.filmlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sergey Laposhko on 18.10.14.
 */
public class FilmListAddToCartCommand extends Command {
    private static final int DEFAULT_RENT_DAYS = 3;
    private static final int DEFAULT_COPIES = 1;

    private static final Logger LOG = Logger.getLogger(FilmListAddToCartCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String addToCartId = request.getParameter(FilmListCommand.ADD_TO_CART_ID_PARAM_NAME);

        if(addToCartId != null && !addToCartId.isEmpty()){
            LOG.debug("FilmListAddToCartCommand started with param = " + addToCartId + ".");
            Rent rent = (Rent) request.getSession().getAttribute(FilmListCommand.CART_PARAM_NAME);
            if(rent == null){
                LOG.debug("No cart object. Creating...");
                rent = new Rent();
                rent.setFilmList(new ArrayList<FilmForRent>());

                request.getSession().setAttribute(FilmListCommand.CART_PARAM_NAME, rent);
                LOG.debug("Cart object created.");
            }
            FilmForRent filmForRent = getFilmForRent(addToCartId);
            rent.getFilmList().add(filmForRent);
            LOG.debug("FilmListAddToCartCommand finished.");
        }

        return null;
    }

    private FilmForRent getFilmForRent(String addToCartId) {
        int filmID = Integer.parseInt(addToCartId);
        DAOFactory df = DAOFactory.getInstance();
        int filmCopiesLeft = df.getFilmRentedDAO().findFilmCopiesAtRent(filmID);

        FilmForRent filmForRent = new FilmForRent();
        filmForRent.setFilmID(filmID);
        filmForRent.setCopies(DEFAULT_COPIES);
        filmForRent.setCopiesLeft(filmCopiesLeft);
        filmForRent.setAcceptedDate(new Date());
        filmForRent.setFutureDate(getFutureDate(new Date()));

        return filmForRent;
    }

    private Date getFutureDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, DEFAULT_RENT_DAYS);
        return c.getTime();
    }
}
