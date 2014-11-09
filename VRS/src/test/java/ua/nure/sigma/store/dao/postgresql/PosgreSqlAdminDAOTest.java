package ua.nure.sigma.store.dao.postgresql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.nure.sigma.store.dao.AdminDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.LocaleDAO;
import ua.nure.sigma.store.entity.Admin;
import static org.easymock.EasyMock.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class PosgreSqlAdminDAOTest {

    static AdminDAO adminDAO;

    @Before
    public void setUp() throws Exception {
//        adminDAO = DAOFactory.getInstance().getAdminDAO();
//        DAOFactory daoFactory = mock(DAOFactory.class);
//        LocaleDAO localeDAO = mock(LocaleDAO.class);
//        AdminDAO adminDAOmock = mock(AdminDAO.class);
//        when(daoFactory.getLocaleDAO()).thenReturn(localeDAO);
//        when(daoFactory.getAdminDAO()).thenReturn(adminDAOmock);
//        expect(DAOFactory.getInstance()).andReturn(daoFactory);
//        replay(DAOFactory.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindAdminByLogin() throws Exception {


    }

    @Test
    public void testFindAdminById() throws Exception {

    }

    @Test
    public void testFindAllAdmins() throws Exception {

    }

    @Test
    public void testFindAllAdmin() throws Exception {

    }

    @Test
    public void testCreateAdmin() throws Exception {

    }

    @Test
    public void testUpdateAdminPassword() throws Exception {

    }

    @Test
    public void testUpdateAdminLocale() throws Exception {

    }

    @Test
    public void testDeleteAdmin() throws Exception {

    }
}