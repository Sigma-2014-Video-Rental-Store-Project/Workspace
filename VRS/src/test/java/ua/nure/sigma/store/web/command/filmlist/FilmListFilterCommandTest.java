package ua.nure.sigma.store.web.command.filmlist;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import ua.nure.sigma.store.states.FilmListFilterAllState;
import ua.nure.sigma.store.web.list.Films;

/**
 * @author Sergey Laposhko
 *
 */
@RunWith(PowerMockRunner.class)
public class FilmListFilterCommandTest {
    
    /**
     * Test method for {@link ua.nure.sigma.store.web.command.filmlist.FilmListFilterCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
     * @throws ServletException 
     * @throws IOException 
     */
    @Test
    public final void testExecute() throws IOException, ServletException {
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	HttpSession session = mock(HttpSession.class);
	
	Films films = mock(Films.class);
	
	when(request.getParameter(FilmListCommand.FILTER_PARAM_NAME)).thenReturn("all");
	when(request.getSession()).thenReturn(session);
	when(session.getAttribute(FilmListCommand.FILMS_PARAM_NAME)).thenReturn(films);
	
	String res = new FilmListFilterCommand().execute(request, response);
	
	verify(films).setFilterState(Mockito.any(FilmListFilterAllState.class));
	
	Assert.assertNull(res);
    }

}
