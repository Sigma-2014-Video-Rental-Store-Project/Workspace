package ua.nure.sigma.store.states;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.sigma.store.entity.Film;


public class FilmListFilterAllStateTest {

    @Test
    public void testGetFilteredList() throws Exception {
	FilmListFilterAllState state = new FilmListFilterAllState();
	List<Film> films = new ArrayList<Film>();
	films.add(new Film());
	Assert.assertEquals("", films, state.getFilteredList(films));
    }

}
