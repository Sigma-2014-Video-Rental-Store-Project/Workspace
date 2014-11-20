package ua.nure.sigma.store.dao.postgresql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.nure.sigma.store.dao.AdminDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.LocaleDAO;
import ua.nure.sigma.store.entity.Admin;
import static org.easymock.EasyMock.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class PostgreSqlAdminDAOTest {

    static AdminDAO adminDAO;
    static Admin admin;
    @Before
    public void setUp() throws Exception {
        adminDAO = DAOFactory.getInstance().getAdminDAO();
        admin = new Admin();
        admin.setEmail("test@com");
        admin.setLocale(1);
        admin.setRoleId(1);
        admin.setPassword("test".hashCode());
//        DAOFactory daoFactory = mock(DAOFactory.class);
//        LocaleDAO localeDAO = mock(LocaleDAO.class);
//        PostgreSqlAdminDAO adminDAOmock = mock(PostgreSqlAdminDAO.class);
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
//        DAOFactory daoFactory = mock(DAOFactory.class);
////        LocaleDAO localeDAO = mock(LocaleDAO.class);
//        PostgreSqlAdminDAO adminDAOmock = mock(PostgreSqlAdminDAO.class);
//        when(daoFactory.getAdminDAO()).thenReturn(adminDAOmock);
//        staticMo
        Admin expect = new Admin();
        assertNotNull(adminDAO.findAdminByLogin("admin1@vrs.com"));

    }

    @Test
    public void testFindAdminById() throws Exception {
        assertNotNull(adminDAO.findAdminById(1));
    }

    @Test
    public void testFindAllAdmins() throws Exception {
        assertNotEquals(adminDAO.findAllAdmins().size(), 0);
    }


    @Test
    public void testCreateAdmin() throws Exception {
        adminDAO.createAdmin(admin);
        adminDAO.updateAdminPassword(admin);
        adminDAO.updateAdminLocale(admin);
        adminDAO.deleteAdmin(admin);
        assertTrue(true);
    }

}