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
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class DeleteAdminCommandTest {

    @Test
    public void testExecute() throws IOException, ServletException {

        // Prepare mocks for representation attributes.
        Admin admin = new Admin();
        admin.setId(1);
        List<Admin> admins = new ArrayList<Admin>();
        admins.add(admin);

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
        when(requestMock.getParameter(DeleteAdminCommand.ADMIN_ID_PARAM_NAME)).thenReturn("1");
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        // Prepare target object.
        DeleteAdminCommand command = new DeleteAdminCommand();
        String result = command.execute(requestMock, responseMock);

        // Verify set attribute call.
        verify(adminDAOMock).deleteAdmin(admin);

        // Test target object.
        assertEquals(Paths.COMMAND_ADMIN_LIST + "&" +
                ChangeAdminPasswordCommand.MESSAGE_ATTRIBUTE_NAME +
                "=" + URLEncoder.encode("Admin with ID = " +
                1 + " has been deleted.", "UTF-8"), result);
    }

}