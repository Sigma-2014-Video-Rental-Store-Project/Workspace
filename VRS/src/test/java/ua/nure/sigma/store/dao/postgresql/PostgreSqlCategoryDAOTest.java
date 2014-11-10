package ua.nure.sigma.store.dao.postgresql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.nure.sigma.store.dao.CategoryDAO;
import ua.nure.sigma.store.dao.DAOFactory;

import static org.junit.Assert.*;

public class PostgreSqlCategoryDAOTest {
    static CategoryDAO dao;
    @Before
    public void setUp() throws Exception {
        dao = DAOFactory.getInstance().getCategoryDAO();

    }

    @Test
    public void testFindCategoryIdByName() throws Exception {
        assertNotNull(dao.findCategoryIdByName("short"));
    }

    @Test
    public void testFindAllCategory() throws Exception {
        assertNotEquals(dao.findAllCategory().size(), 0);
    }

    @Test
    public void testFindAllCategoryLocale() throws Exception {
        assertNotEquals(dao.findAllCategory(1).size(),0);
    }

    @Test
    public void testFindCategoryByID() throws Exception {
        assertNotNull(dao.findCategoryByID(1));
    }

    @Test
    public void testFindCategoryByIDLocale() throws Exception {
        assertNotNull(dao.findCategoryByID(1, 1));
    }


}