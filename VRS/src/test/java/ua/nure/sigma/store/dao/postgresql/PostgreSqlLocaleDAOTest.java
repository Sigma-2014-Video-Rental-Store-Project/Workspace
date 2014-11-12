package ua.nure.sigma.store.dao.postgresql;

import org.junit.Before;
import org.junit.Test;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.LocaleDAO;

import static org.junit.Assert.*;

public class PostgreSqlLocaleDAOTest {
    static LocaleDAO dao;
    @Before
    public void setUp() throws Exception {
        dao = DAOFactory.getInstance().getLocaleDAO();

    }

    @Test
    public void testFindLocaleIdByName() throws Exception {
        assertNotNull(dao.findLocaleIdByName("en"));
    }

    @Test
    public void testFindLocaleNameById() throws Exception {
        assertNotNull(dao.findLocaleNameById(1));
    }

    @Test
    public void testFindAlLocale() throws Exception {
        assertNotEquals(dao.findAlLocale().size(),0);
    }
}