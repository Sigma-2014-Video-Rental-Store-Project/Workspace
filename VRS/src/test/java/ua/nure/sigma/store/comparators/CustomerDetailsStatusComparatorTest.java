package ua.nure.sigma.store.comparators;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import ua.nure.sigma.store.entity.CustomerDetails;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;

public class CustomerDetailsStatusComparatorTest {

	@Test
	public void testCompare() throws NotEnoughOfBonusExeption {
		CustomerDetailsStatusComparator c = new CustomerDetailsStatusComparator();
		CustomerDetails cd1 = new CustomerDetails();
		CustomerDetails cd2 = new CustomerDetails();
		
		cd1.setFilmForRent(new FilmForRent());
		cd2.setFilmForRent(new FilmForRent());
		
		cd1.setAcceptedDate(null);
		cd2.setAcceptedDate(null);
		assertTrue("CustomerDetailsStatusComparator !equals! error", c.compare(cd1, cd2) == 0);
		cd1.setAcceptedDate(new Date());
		cd2.setAcceptedDate(null);
		assertTrue("CustomerDetailsStatusComparator !more! error", c.compare(cd1, cd2) < 0);
		cd1.setAcceptedDate(null);
		cd2.setAcceptedDate(new Date());
		assertTrue("CustomerDetailsStatusComparator !less! error", c.compare(cd1, cd2) > 0);
		cd1.setAcceptedDate(new Date());
		cd2.setAcceptedDate(new Date());
		assertTrue("CustomerDetailsStatusComparator !equals! error", c.compare(cd1, cd2) == 0);
	}
	
}
