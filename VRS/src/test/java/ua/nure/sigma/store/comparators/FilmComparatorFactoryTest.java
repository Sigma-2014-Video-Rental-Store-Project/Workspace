package ua.nure.sigma.store.comparators;

import static org.junit.Assert.*;

import org.junit.Test;

public class FilmComparatorFactoryTest {

	@Test
	public final void testGetComparator() {
		new FilmComparatorFactory();
		assertNotNull(
				"Comparator mustn't be ",
				FilmComparatorFactory
						.getComparator(FilmComparatorFactory.FILM_COPIES_LEFT_COMPARATOR));
	}

}
