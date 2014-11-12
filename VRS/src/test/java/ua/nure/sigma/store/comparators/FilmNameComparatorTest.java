package ua.nure.sigma.store.comparators;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;

public class FilmNameComparatorTest {

	@Test
	public void testCompare() throws NotEnoughOfBonusExeption {
		FilmNameComparator comp = new FilmNameComparator();
		Film f1 = new Film();
		Film f2 = new Film();
		
		String testString1 = "test string 1";
		String testString2 = "test string 2";
		
		f1.setTitle(null);
		f2.setTitle(null);
		assertTrue("CustomerDetailsStatusComparator !equals! error", comp.compare(f1, f2) == 0);
		f1.setTitle(null);
		f2.setTitle("a");
		assertTrue("CustomerDetailsStatusComparator !less! null error", comp.compare(f1, f2) < 0);
		f1.setTitle("a");
		f2.setTitle(null);
		assertTrue("CustomerDetailsStatusComparator !more! null error", comp.compare(f1, f2) > 0);
		f1.setTitle(testString1);
		f2.setTitle(testString2);
		assertEquals("CustomerDetailsStatusComparator !equals! error", testString1.compareTo(testString2),
			comp.compare(f1, f2));
	}

}
