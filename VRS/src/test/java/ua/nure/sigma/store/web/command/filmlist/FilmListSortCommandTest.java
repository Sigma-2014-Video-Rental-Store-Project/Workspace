package ua.nure.sigma.store.web.command.filmlist;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ua.nure.sigma.store.comparators.FilmComparatorFactory;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.list.Films;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class FilmListSortCommandTest {
    
    @Test
    public final void testExecute() throws IOException, ServletException {
	List<Film> list = new ArrayList<Film>();
	Film film1 = new Film();
	film1.setTitle("asd");
	Film film2 = new Film();
	film2.setTitle("zsda");
	Film film3 = new Film();
	film3.setTitle("asda");
	list.add(film1);
	list.add(film2);
	list.add(film3);
	Films films = new Films(list);
	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	
	when(requestMock.getParameter(FilmListCommand.SORT_PARAM_NAME)).thenReturn(FilmComparatorFactory.FILM_NAME_COMPARATOR);
	when(requestMock.getParameter(FilmListCommand.DIRECT_PARAM_NAME)).thenReturn(FilmListSortCommand.DOWN_DIR);
	when(requestMock.getSession()).thenReturn(sessionMock);
	
	when(sessionMock.getAttribute(FilmListCommand.FILMS_PARAM_NAME)).thenReturn(films);
	
	FilmListSortCommand command = new FilmListSortCommand();
	command.execute(requestMock, responseMock);
	
	for(int i = 0; i < list.size() - 1; i++){
	    Film curFilm = list.get(i);
	    Film nextFilm = list.get(i + 1);
	    assertTrue(curFilm.getTitle().compareTo(nextFilm.getTitle()) >= 0);
	}
    }

}
