package ua.nure.sigma.store.web.command.filmlist;

import static org.easymock.EasyMock.expect;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmRentedDAO;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.logic.Cart;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class FilmListAddToCartCommandTest {

    @Test
    public void testExecute() throws Exception {
	int id = 1;
	int countOfCopies = 5;
	//Films
	List<Film> films = new ArrayList<Film>();
	films.add(new Film());
	films.add(new Film());
	
        // FilmRentedDAO mock.
        FilmRentedDAO filmRentedDAOMock = mock(FilmRentedDAO.class);
        when(filmRentedDAOMock.findFilmCopiesAtRent(id)).thenReturn(countOfCopies);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmRentedDAO()).thenReturn(filmRentedDAOMock);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute(FilmListCommand.CART_PARAM_NAME)).thenReturn(null);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(requestMock.getParameter(FilmListCommand.ADD_TO_CART_ID_PARAM_NAME)).thenReturn(String.valueOf(id));
        HttpServletResponse responseMock = mock(HttpServletResponse.class);
	
        //Command
	FilmListAddToCartCommand addToCartCommand = new FilmListAddToCartCommand();
	String res = addToCartCommand.execute(requestMock, responseMock);
	
	verify(sessionMock).setAttribute(Matchers.eq(FilmListCommand.CART_PARAM_NAME), any(Cart.class));
    }

}
