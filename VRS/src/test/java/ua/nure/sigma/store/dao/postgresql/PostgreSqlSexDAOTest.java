package ua.nure.sigma.store.dao.postgresql;

import org.junit.Before;
import org.junit.Test;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.SexDAO;

import static org.junit.Assert.*;

public class PostgreSqlSexDAOTest {

    static SexDAO dao;
    @Before
    public void setUp() throws Exception {
        dao = DAOFactory.getInstance().getSexDAO();
    }

    @Test
    public void testFindSexByID() throws Exception {
        assertNotNull(dao.findSexByID(1,2));
    }

    @Test
    public void testFindAllSex() throws Exception {
        assertNotEquals(dao.findAllSex(1).size(), 0);
    }

    @Test
    public void testFindSexIDBySexName() throws Exception {
        assertNotNull(dao.findSexIDBySexName("male"));
    }
}