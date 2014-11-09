package ua.nure.sigma.store.web.command.adminList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.nure.sigma.store.dao.AdminDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.web.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class ChangeAdminPasswordCommandTest {

    @Test
    public void testExecutePass() throws IOException, ServletException {

        // Prepare mocks for representation attributes.
        Admin admin = new Admin();
        admin.setId(1);
        admin.setEmail("admin@vrs.com");

        // AdminDAO mock.
        AdminDAO adminDAOMock = mock(AdminDAO.class);
        when(adminDAOMock.findAdminById(1)).thenReturn(admin);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getAdminDAO()).thenReturn(adminDAOMock);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter(ChangeAdminPasswordCommand.ADMIN_ID_PARAM_NAME)).thenReturn("1");
        when(requestMock.getParameter(ChangeAdminPasswordCommand.ADMIN_PASSWORD_PARAM_NAME)).thenReturn("password");
        when(requestMock.getParameter(ChangeAdminPasswordCommand.ADMIN_PASSWORD_RETYPE_PARAM_NAME)).thenReturn("password");
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        // Prepare target object.
        ChangeAdminPasswordCommand command = new ChangeAdminPasswordCommand();
        String result = command.execute(requestMock, responseMock);

        // Verify set attribute call.
        verify(adminDAOMock).updateAdminPassword(admin);

        // Test target object.
        assertEquals(Paths.COMMAND_ADMIN_LIST + "&"
                + ChangeAdminPasswordCommand.MESSAGE_ATTRIBUTE_NAME + "="
                + URLEncoder.encode(admin.getEmail()
                + " password has been changed.", "UTF-8"), result);
    }

    @Test
    public void testExecuteEmptyPassword() throws IOException, ServletException {

        // Prepare mocks for representation attributes.
        Admin admin = new Admin();
        admin.setId(1);
        admin.setEmail("admin@vrs.com");

        // AdminDAO mock.
        AdminDAO adminDAOMock = mock(AdminDAO.class);
        when(adminDAOMock.findAdminById(1)).thenReturn(admin);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getAdminDAO()).thenReturn(adminDAOMock);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter(ChangeAdminPasswordCommand.ADMIN_ID_PARAM_NAME)).thenReturn("1");
        when(requestMock.getParameter(ChangeAdminPasswordCommand.ADMIN_PASSWORD_PARAM_NAME)).thenReturn("");
        when(requestMock.getParameter(ChangeAdminPasswordCommand.ADMIN_PASSWORD_RETYPE_PARAM_NAME)).thenReturn("password");
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        // Prepare target object.
        ChangeAdminPasswordCommand command = new ChangeAdminPasswordCommand();
        String result = command.execute(requestMock, responseMock);

        // Test target object.
        assertEquals(Paths.COMMAND_ADMIN_LIST + "&"
                + ChangeAdminPasswordCommand.MESSAGE_ATTRIBUTE_NAME
                + "=" + URLEncoder.encode("Empty password field", "UTF-8"), result);
    }

    @Test
    public void testExecutePasswordsDoNotMatch() throws IOException, ServletException {

        // Prepare mocks for representation attributes.
        Admin admin = new Admin();
        admin.setId(1);
        admin.setEmail("admin@vrs.com");

        // AdminDAO mock.
        AdminDAO adminDAOMock = mock(AdminDAO.class);
        when(adminDAOMock.findAdminById(1)).thenReturn(admin);

        // DAOFactory mock;
        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getAdminDAO()).thenReturn(adminDAOMock);

        // Prepare DAOFactory for search of entity.
        mockStatic(DAOFactory.class);
        expect(DAOFactory.getInstance()).andReturn(daoFactory);
        replay(DAOFactory.class);

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter(ChangeAdminPasswordCommand.ADMIN_ID_PARAM_NAME)).thenReturn("1");
        when(requestMock.getParameter(ChangeAdminPasswordCommand.ADMIN_PASSWORD_PARAM_NAME)).thenReturn("password");
        when(requestMock.getParameter(ChangeAdminPasswordCommand.ADMIN_PASSWORD_RETYPE_PARAM_NAME)).thenReturn("passwordOther");
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        // Prepare target object.
        ChangeAdminPasswordCommand command = new ChangeAdminPasswordCommand();
        String result = command.execute(requestMock, responseMock);

        // Test target object.
        assertEquals(Paths.COMMAND_ADMIN_LIST + "&"
                + ChangeAdminPasswordCommand.MESSAGE_ATTRIBUTE_NAME
                + "=" + URLEncoder.encode("Passwords do not match each other.", "UTF-8"), result);
    }
}