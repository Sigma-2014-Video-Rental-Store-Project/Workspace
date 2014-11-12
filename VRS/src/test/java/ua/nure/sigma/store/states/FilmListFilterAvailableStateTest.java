package ua.nure.sigma.store.states;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.sigma.store.entity.Film;

public class FilmListFilterAvailableStateTest {

    @Test
    public void testGetFilteredList() throws Exception {
	List<Film> films = new ArrayList<Film>();
	Film avail = new Film();
	avail.setCopiesLeft(1);
	Film rented = new Film();
	rented.setCopiesLeft(0);
	films.add(avail);
	films.add(rented);
	
	List<Film> actuaList = new FilmListFilterAvailableState().getFilteredList(films);
	for(Film f : actuaList){
	    Assert.assertTrue("Filter available films state error.", f.getCopiesLeft() > 0);
	}
    }

}
