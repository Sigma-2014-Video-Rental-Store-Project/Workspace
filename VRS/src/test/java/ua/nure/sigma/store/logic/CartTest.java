package ua.nure.sigma.store.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Film;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class CartTest {

    @Test
    public void testContains() throws Exception {

        // Existing film and its id.
        int existingFilmId = 1;
        Film existingFilm = new Film();
        existingFilm.setFilmId(existingFilmId);

        // Non-existing film id.
        int nonExistingFilmId = -1;

        // FilmDAO mock.
        FilmDAO filmDAO = mock(FilmDAO.class);
        when(filmDAO.findFilmById(existingFilmId)).thenReturn(existingFilm);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmDAO()).thenReturn(filmDAO);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Set up target object.
        Cart cart = new Cart();
        cart.addNewFilm(existingFilmId);

        // Target assertions.
        assertTrue(cart.contains(existingFilmId));
        assertFalse(cart.contains(nonExistingFilmId));
    }

    @Test
    public void testGetContent() throws Exception {

        // Existing film and its id.
        int existingFilmId = 1;
        Film existingFilm = new Film();
        existingFilm.setFilmId(existingFilmId);

        // FilmDAO mock.
        FilmDAO filmDAO = mock(FilmDAO.class);
        when(filmDAO.findFilmById(existingFilmId)).thenReturn(existingFilm);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmDAO()).thenReturn(filmDAO);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Test target object.
        Cart cart = new Cart();
        assertEquals(0, cart.getContent().size());
        cart.addNewFilm(existingFilmId);
        assertEquals(1, cart.getContent().size());
    }

    @Test
    public void testGetCount() throws Exception {

        // Existing film and its id.
        int existingFilmId = 1;
        Film existingFilm = new Film();
        existingFilm.setFilmId(existingFilmId);

        // FilmDAO mock.
        FilmDAO filmDAO = mock(FilmDAO.class);
        when(filmDAO.findFilmById(existingFilmId)).thenReturn(existingFilm);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmDAO()).thenReturn(filmDAO);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        //  Test target object.
        Cart cart = new Cart();
        assertEquals(0, cart.getCount());
        cart.addNewFilm(existingFilmId);
        assertEquals(1, cart.getCount());
    }

    @Test
    public void testGetTotalCost() throws Exception {

        // Existing film and its id.
        final int existingFilmId = 1;
        final int rentPrice = 100;
        Film existingFilm = new Film();
        existingFilm.setFilmId(existingFilmId);
        existingFilm.setRentPrice(rentPrice);

        // FilmDAO mock.
        FilmDAO filmDAO = mock(FilmDAO.class);
        when(filmDAO.findFilmById(existingFilmId)).thenReturn(existingFilm);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmDAO()).thenReturn(filmDAO);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        //  Test target object.
        Cart cart = new Cart();
        assertEquals(0, cart.getTotalCost());
        cart.addNewFilm(existingFilmId);
        assertEquals(rentPrice, cart.getTotalCost());
    }

    @Test
    public void testGetBonusForRent() throws Exception {

        // Existing film and its id.
        final int existingFilmId = 1;
        final int bonusForRent = 100;
        Film existingFilm = new Film();
        existingFilm.setFilmId(existingFilmId);
        existingFilm.setBonusForRent(bonusForRent);

        // FilmDAO mock.
        FilmDAO filmDAO = mock(FilmDAO.class);
        when(filmDAO.findFilmById(existingFilmId)).thenReturn(existingFilm);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmDAO()).thenReturn(filmDAO);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        //  Test target object.
        Cart cart = new Cart();
        assertEquals(0, cart.getBonusForRent());
        cart.addNewFilm(existingFilmId);
        assertEquals(bonusForRent, cart.getBonusForRent());
    }

    @Test
    public void testAddNewFilm() throws Exception {

        // Existing film and its id.
        int existingFilmId = 1;
        Film existingFilm = new Film();
        existingFilm.setFilmId(existingFilmId);

        // FilmDAO mock.
        FilmDAO filmDAO = mock(FilmDAO.class);
        when(filmDAO.findFilmById(existingFilmId)).thenReturn(existingFilm);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmDAO()).thenReturn(filmDAO);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        //  Test target object.
        Cart cart = new Cart();
        cart.addNewFilm(existingFilmId);
        assertTrue(cart.contains(existingFilmId));

        // Try to add this film twice - ignore.
        cart.addNewFilm(existingFilmId);
        assertTrue(cart.contains(existingFilmId));
    }

    @Test
    public void testClear() throws Exception {

        // Existing film and its id.
        int existingFilmId = 1;
        Film existingFilm = new Film();
        existingFilm.setFilmId(existingFilmId);

        // FilmDAO mock.
        FilmDAO filmDAO = mock(FilmDAO.class);
        when(filmDAO.findFilmById(existingFilmId)).thenReturn(existingFilm);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmDAO()).thenReturn(filmDAO);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        //  Test target object.
        Cart cart = new Cart();
        assertEquals(0, cart.getCount());
        cart.addNewFilm(existingFilmId);
        assertEquals(1, cart.getCount());
        cart.clear();
        assertEquals(0, cart.getCount());
    }

    @Test
    public void testSetAndGetCurrentCustomer() throws Exception {

        // Existing customer.
        Customer customer = new Customer();

        //  Test target object.
        Cart cart = new Cart();
        cart.setCurrentCustomer(customer);
        assertEquals(customer, cart.getCurrentCustomer());
    }

    @Test
    public void testRemove() throws Exception {

        // Existing film and its id.
        int existingFilmId = 1;
        Film existingFilm = new Film();
        existingFilm.setFilmId(existingFilmId);
        int nonExistingFilmId = -1;

        // FilmDAO mock.
        FilmDAO filmDAO = mock(FilmDAO.class);
        when(filmDAO.findFilmById(existingFilmId)).thenReturn(existingFilm);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getFilmDAO()).thenReturn(filmDAO);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        //  Test target object.
        Cart cart = new Cart();
        assertEquals(0, cart.getCount());
        cart.addNewFilm(existingFilmId);
        assertEquals(1, cart.getCount());
        cart.remove(existingFilmId);
        assertEquals(0, cart.getCount());

        // Try to remove non-existing film - ignore.
        cart.remove(nonExistingFilmId);
        assertEquals(0, cart.getCount());
    }
}