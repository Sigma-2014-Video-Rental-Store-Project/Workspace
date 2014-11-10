package ua.nure.sigma.store.dao.postgresql;

import org.junit.Before;
import org.junit.Test;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.RoleDAO;

import static org.junit.Assert.*;

public class PostgreSqlRoleDAOTest {
    static RoleDAO dao;
    @Before
    public void setUp() throws Exception {
        dao = DAOFactory.getInstance().getRoleDAO();

    }

    @Test
    public void testFindRoleByID() throws Exception {
        assertNotNull(dao.findRoleByID(1));
    }

    @Test
    public void testFindRoleByName() throws Exception {
        assertNotNull(dao.findRoleByName("SuperAdmin"));
    }

    @Test
    public void testFindAllRole() throws Exception {
        assertNotEquals(dao.findAllRole().size(),0);
    }
}