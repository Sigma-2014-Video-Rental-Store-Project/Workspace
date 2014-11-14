package ua.nure.sigma.store.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FilmTest {

    private static final int ID = 1;
    private static final String TITLE = "title";
    private static final int YEAR = 1;
    private static final String DESCRIPTION = "desc";
    private static final String COVER = "cover";
    private static final int AMOUNT = 1;
    private static final long GENERAL_PRICE = 1;
    private static final long RENT_PRICE = 1;
    private static final long BONUS = 1;
    private static final int COPIES_LEFT = 1;

    @Test
    public void testFilmSetters() throws Exception {
	Film film = new Film();
	film.setFilmId(ID);
	film.setTitle(TITLE);
	film.setYear(YEAR);
	film.setDescription(DESCRIPTION);
	film.setCover(COVER);
	film.setAmount(AMOUNT);
	film.setGeneralPrice(GENERAL_PRICE);
	film.setRentPrice(RENT_PRICE);
	film.setBonusForRent(BONUS);
	film.setCopiesLeft(COPIES_LEFT);

	assertEquals("File getter setter error.", ID, film.getFilmId());
	assertEquals("File getter setter error.", TITLE, film.getTitle());
	assertEquals("File getter setter error.", YEAR, film.getYear());
	assertEquals("File getter setter error.", DESCRIPTION, film.getDescription());
	assertEquals("File getter setter error.", COVER, film.getCover());
	assertEquals("File getter setter error.", AMOUNT, film.getAmount());
	assertEquals("File getter setter error.", GENERAL_PRICE, film.getGeneralPrice());
	assertEquals("File getter setter error.", RENT_PRICE, film.getRentPrice());
	assertEquals("File getter setter error.", BONUS, film.getBonusForRent());
	assertEquals("File getter setter error.", COPIES_LEFT, film.getCopiesLeft());
    }
    
    @Test
    public void testToString(){
	new Film().toString();
    }
}
