package ua.nure.sigma.store.web.command.filmlist;

import org.apache.catalina.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmRentedDAO;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.web.command.adminList.AdminListCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

/**
 * Created by Maksim Sinkevich on 11.11.2014.
 */


@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class FilmListAddToCartCommandTest {
    private static final String ADD_TO_CART_ID = "1";
    private static final int DEFAULT_RENT_DAYS = 3;
    private static final int DEFAULT_COPIES = 1;

    @Test
    public void testExecute() throws IOException, ServletException {
        FilmRentedDAO filmRentedDAOMock = mock(FilmRentedDAO.class);
        int filmID = Integer.parseInt(ADD_TO_CART_ID);
        int filmCopiesLeft = 0;
        when(filmRentedDAOMock.findFilmCopiesAtRent(filmID)).thenReturn(filmCopiesLeft);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmRentedDAO()).thenReturn(filmRentedDAOMock);

        HttpSession session = mock(HttpSession.class);

        Rent rent = new Rent();
        rent.setFilmList(new ArrayList<FilmForRent>());

        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        FilmForRent filmForRent = new FilmForRent();
        filmForRent.setFilmID(filmID);
        filmForRent.setCopies(DEFAULT_COPIES);
        filmForRent.setCopiesLeft(filmCopiesLeft);
        filmForRent.setAcceptedDate(new Date());

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, DEFAULT_RENT_DAYS);
        filmForRent.setFutureDate(c.getTime());
        rent.getFilmList().add(filmForRent);

        FilmListAddToCartCommand filmListAddToCartCommand = new FilmListAddToCartCommand();

        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter(FilmListCommand.ADD_TO_CART_ID_PARAM_NAME)).thenReturn("1");
        when(requestMock.getSession()).thenReturn(session);
        when(requestMock.getSession().getAttribute(FilmListCommand.CART_PARAM_NAME)).thenReturn(rent);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        Object result = filmListAddToCartCommand.execute(requestMock, responseMock);



        assertEquals(null, result);
    }
}
