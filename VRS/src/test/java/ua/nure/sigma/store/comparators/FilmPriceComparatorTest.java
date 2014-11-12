package ua.nure.sigma.store.comparators;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;

public class FilmPriceComparatorTest {

	@Test
	public void testCompare() throws NotEnoughOfBonusExeption {
		FilmPriceComparator comp = new FilmPriceComparator();
		Film f1 = new Film();
		Film f2 = new Film();
		
		f1.setRentPrice(1);
		f2.setRentPrice(1);
		assertTrue("CustomerDetailsStatusComparator !equals! error", comp.compare(f1, f2) == 0);
		f1.setRentPrice(1);
		f2.setRentPrice(0);
		assertTrue("CustomerDetailsStatusComparator !more! error", comp.compare(f1, f2) > 0);
		f1.setRentPrice(0);
		f2.setRentPrice(1);
		assertTrue("CustomerDetailsStatusComparator !less! error", comp.compare(f1, f2) < 0);
	}
}
