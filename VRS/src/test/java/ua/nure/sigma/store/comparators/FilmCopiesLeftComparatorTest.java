package ua.nure.sigma.store.comparators;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;

public class FilmCopiesLeftComparatorTest {

	@Test
	public void testCompare() throws NotEnoughOfBonusExeption {
		FilmCopiesLeftComparator comp = new FilmCopiesLeftComparator();
		Film f1 = new Film();
		Film f2 = new Film();
		
		f1.setCopiesLeft(1);
		f2.setCopiesLeft(1);
		assertTrue("CustomerDetailsStatusComparator !equals! error", comp.compare(f1, f2) == 0);
		f1.setCopiesLeft(1);
		f2.setCopiesLeft(0);
		assertTrue("CustomerDetailsStatusComparator !more! error", comp.compare(f1, f2) > 0);
		f1.setCopiesLeft(0);
		f2.setCopiesLeft(1);
		assertTrue("CustomerDetailsStatusComparator !less! error", comp.compare(f1, f2) < 0);
	}
}
