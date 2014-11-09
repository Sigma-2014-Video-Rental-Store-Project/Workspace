package ua.nure.sigma.store.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DAOFactoryTest {
    static DAOFactory daoFactory;
    static Connection connection;
    @Before
    public void setUp() throws Exception {
        daoFactory = DAOFactory.getInstance();

    }

    @Test
    public void testGetInstance() throws Exception {
        assertEquals(daoFactory, DAOFactory.getInstance());
    }

    @Test
    public void testGetConnection() throws Exception {
        connection = DAOFactory.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testGetAdminDAO() throws Exception {
        assertNotNull(daoFactory.getAdminDAO());
    }

    @Test
    public void testGetCustomerDAO() throws Exception {
        assertNotNull(daoFactory.getCustomerDAO());
    }

    @Test
    public void testGetCategoryDAO() throws Exception {
        assertNotNull(daoFactory.getCategoryDAO());
    }

    @Test
    public void testGetFilmDAO() throws Exception {
        assertNotNull(daoFactory.getFilmDAO());
    }

    @Test
    public void testGetRentDAO() throws Exception {
        assertNotNull(daoFactory.getRentDAO());
    }

    @Test
    public void testGetRoleDAO() throws Exception {
        assertNotNull(daoFactory.getRoleDAO());
    }

    @Test
    public void testGetFilmCategoryDAO() throws Exception {
        assertNotNull(daoFactory.getFilmCategoryDAO());
    }

    @Test
    public void testGetSexDAO() throws Exception {
        assertNotNull(daoFactory.getSexDAO());
    }

    @Test
    public void testGetFilmRentedDAO() throws Exception {
        assertNotNull(daoFactory.getFilmRentedDAO());
    }

    @Test
    public void testGetLocaleDAO() throws Exception {
        assertNotNull(daoFactory.getLocaleDAO());
    }

    @After
    public void tearDown() throws Exception {
        DAOFactory.commitAndClose(connection);

    }

    @Test
    public void testCommitAndClose() throws Exception {
        Connection con = mock(Connection.class);
        DAOFactory.commitAndClose(con);
        verify(con).commit();
        verify(con).close();
    }
    @Test
    public void testCloseResultSet() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        DAOFactory.close(resultSet);
        verify(resultSet).close();
    }
    @Test
    public void testCloseStatement() throws Exception {
        Statement st = mock(Statement.class);
        DAOFactory.close(st);
        verify(st).close();
    }
    @Test
    public void testRollback() throws Exception {
        Connection con = mock(Connection.class);
        DAOFactory.rollback(con);
        verify(con).rollback();
    }
}