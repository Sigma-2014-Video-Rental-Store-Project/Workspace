package ua.nure.sigma.store.web.command.addNewAdmin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.nure.sigma.store.dao.AdminDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.LocaleDAO;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.web.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class CreateNewAdminCommandTest {

    @Test
    public void testExecutePass() throws IOException, ServletException {

        // Expected object.
        Admin admin = new Admin();
        admin.setEmail("email@vrs.com");
        admin.setPassword("password".hashCode());
        admin.setRoleId(2);
        admin.setLocale(1);

        // LocaleDAO mock.
        LocaleDAO localeDAOMock = mock(LocaleDAO.class);
        when(localeDAOMock.findLocaleIdByName("en")).thenReturn(1);

        // AdminDAO mock.
        AdminDAO adminDAOMock = mock(AdminDAO.class);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getLocaleDAO()).thenReturn(localeDAOMock);
        when(daoFactory.getAdminDAO()).thenReturn(adminDAOMock);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter("email")).thenReturn("email@vrs.com");
        when(requestMock.getParameter("password")).thenReturn("password");
        when(requestMock.getParameter("passwordRetype")).thenReturn("password");
        when(requestMock.getParameter("locale")).thenReturn("en");
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        // Prepare target object.
        CreateNewAdminCommand command = new CreateNewAdminCommand();
        String result = command.execute(requestMock, responseMock);

        // Verify creation call.
        verify(adminDAOMock).createAdmin(any(Admin.class));

        // Test target object.
        assertEquals(Paths.COMMAND_ADMIN_LIST, result);
    }

    @Test
    public void testExecuteEmptyEmail() throws IOException, ServletException {

        // LocaleDAO mock.
        LocaleDAO localeDAOMock = mock(LocaleDAO.class);
        when(localeDAOMock.findLocaleIdByName("en")).thenReturn(1);

        // AdminDAO mock.
        AdminDAO adminDAOMock = mock(AdminDAO.class);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getLocaleDAO()).thenReturn(localeDAOMock);
        when(daoFactory.getAdminDAO()).thenReturn(adminDAOMock);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter("email")).thenReturn("");
        when(requestMock.getParameter("password")).thenReturn("password");
        when(requestMock.getParameter("passwordRetype")).thenReturn("password");
        when(requestMock.getParameter("locale")).thenReturn("en");
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        // Prepare target object.
        CreateNewAdminCommand command = new CreateNewAdminCommand();
        String result = command.execute(requestMock, responseMock);

        // Test target object.
        assertEquals(Paths.COMMAND_ADD_NEW_ADMIN + "&" + CreateNewAdminCommand.ERR_MES_PARAM +
                "=" + URLEncoder.encode("Specified email is empty.", "UTF-8"), result);
    }

    @Test
    public void testExecuteAdminExists() throws IOException, ServletException {

        // Expected object.
        Admin admin = new Admin();
        admin.setEmail("email@vrs.com");
        admin.setPassword("password".hashCode());
        admin.setRoleId(2);
        admin.setLocale(1);

        // LocaleDAO mock.
        LocaleDAO localeDAOMock = mock(LocaleDAO.class);
        when(localeDAOMock.findLocaleIdByName("en")).thenReturn(1);

        // AdminDAO mock.
        AdminDAO adminDAOMock = mock(AdminDAO.class);
        when(adminDAOMock.findAdminByLogin("email@vrs.com")).thenReturn(admin);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getLocaleDAO()).thenReturn(localeDAOMock);
        when(daoFactory.getAdminDAO()).thenReturn(adminDAOMock);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter("email")).thenReturn("email@vrs.com");
        when(requestMock.getParameter("password")).thenReturn("password");
        when(requestMock.getParameter("passwordRetype")).thenReturn("password");
        when(requestMock.getParameter("locale")).thenReturn("en");
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        // Prepare target object.
        CreateNewAdminCommand command = new CreateNewAdminCommand();
        String result = command.execute(requestMock, responseMock);

        // Test target object.
        assertEquals(Paths.COMMAND_ADD_NEW_ADMIN + "&" + CreateNewAdminCommand.ERR_MES_PARAM +
                "=" + URLEncoder.encode("Specified email is already in use.", "UTF-8"), result);
    }

    @Test
    public void testExecuteEmptyPassword() throws IOException, ServletException {

        // LocaleDAO mock.
        LocaleDAO localeDAOMock = mock(LocaleDAO.class);
        when(localeDAOMock.findLocaleIdByName("en")).thenReturn(1);

        // AdminDAO mock.
        AdminDAO adminDAOMock = mock(AdminDAO.class);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getLocaleDAO()).thenReturn(localeDAOMock);
        when(daoFactory.getAdminDAO()).thenReturn(adminDAOMock);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter("email")).thenReturn("email@vrs.com");
        when(requestMock.getParameter("password")).thenReturn("");
        when(requestMock.getParameter("passwordRetype")).thenReturn("password");
        when(requestMock.getParameter("locale")).thenReturn("en");
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        // Prepare target object.
        CreateNewAdminCommand command = new CreateNewAdminCommand();
        String result = command.execute(requestMock, responseMock);

        // Test target object.
        assertEquals(Paths.COMMAND_ADD_NEW_ADMIN + "&" + CreateNewAdminCommand.ERR_MES_PARAM +
                "=" + URLEncoder.encode("Specified password must not be empty.", "UTF-8"), result);
    }

    @Test
    public void testExecutePasswordsDoNotMatch() throws IOException, ServletException {

        // LocaleDAO mock.
        LocaleDAO localeDAOMock = mock(LocaleDAO.class);
        when(localeDAOMock.findLocaleIdByName("en")).thenReturn(1);

        // AdminDAO mock.
        AdminDAO adminDAOMock = mock(AdminDAO.class);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getLocaleDAO()).thenReturn(localeDAOMock);
        when(daoFactory.getAdminDAO()).thenReturn(adminDAOMock);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter("email")).thenReturn("email@vrs.com");
        when(requestMock.getParameter("password")).thenReturn("password");
        when(requestMock.getParameter("passwordRetype")).thenReturn("passwordNew");
        when(requestMock.getParameter("locale")).thenReturn("en");
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        // Prepare target object.
        CreateNewAdminCommand command = new CreateNewAdminCommand();
        String result = command.execute(requestMock, responseMock);

        // Test target object.
        assertEquals(Paths.COMMAND_ADD_NEW_ADMIN + "&" + CreateNewAdminCommand.ERR_MES_PARAM +
                "=" + URLEncoder.encode("Two passwords must match each other.", "UTF-8"), result);
    }
}