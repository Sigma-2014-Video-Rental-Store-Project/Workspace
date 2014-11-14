package ua.nure.sigma.store.web.command.filmlist;

import org.junit.Assert;
import org.junit.Test;

public class FilmListCommandInitializerTest {

    @Test
    public void testGetCommand() throws Exception {
	Assert.assertNotNull(FilmListCommandInitializer.getCommand());
    }

    @Test
    public void testConstructor(){
	new FilmListCommandInitializer();
    }
    
}
