package ua.nure.sigma.store.web.command.addNewAdmin;

import org.junit.Test;
import ua.nure.sigma.store.web.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AddNewAdminCommandTest {

    @Test
    public void testExecute() throws IOException, ServletException {

        // Prepare mocks for request and response.
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        // Prepare target object.
        AddNewAdminCommand command = new AddNewAdminCommand();
        String result = command.execute(requestMock, responseMock);

        // Test target object.
        assertEquals(Paths.PAGE_ADD_NEW_ADMIN, result);
    }
}