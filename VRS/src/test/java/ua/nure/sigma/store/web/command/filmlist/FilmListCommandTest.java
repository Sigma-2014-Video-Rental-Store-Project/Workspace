package ua.nure.sigma.store.web.command.filmlist;

import static org.easymock.EasyMock.expect;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class FilmListCommandTest {

    @Test
    public void testExecute() throws Exception {
	//Films
	List<Film> films = new ArrayList<Film>();
	films.add(new Film());
	films.add(new Film());
	
        // FilmDAO mock.
        FilmDAO filmDAOMock = mock(FilmDAO.class);
        when(filmDAOMock.findAllFilms()).thenReturn(films);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmDAO()).thenReturn(filmDAOMock);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute(FilmListCommand.ALL_FILMS_PARAM_NAME)).thenReturn(null);
        when(requestMock.getSession()).thenReturn(sessionMock);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);
	
        //Command
	Command command1 = mock(Command.class);
	FilmListCommand flc = new FilmListCommand();
	flc.addCommandListener(command1);
	String res = flc.execute(requestMock, responseMock);
	
	//verify
	// TODO verify setting parameters in the sessionMock
	verify(command1).execute(requestMock, responseMock);
	Assert.assertEquals("Wrong return path", Paths.PAGE_FILM_LIST, res);
    }

    @Test
    public void testFilmListCommand() throws Exception {
	new FilmListCommand();
    }

}
