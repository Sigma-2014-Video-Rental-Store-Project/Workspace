package ua.nure.sigma.store.web;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ua.nure.sigma.store.entity.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ControllerTest {

    @Test
    public void testController() throws ServletException, IOException {
        final String USER_ATTRIBUTE_NAME = "user";
        final String COMMAND_PARAMETER_NAME = "command";
        final String COMMAND_TO_EXECUTE_NAME = "wrong";
        final String REDIRECT_TO = "nowhere";

        RequestDispatcher mockRequestDispatcher = mock(RequestDispatcher.class);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                System.out.println(Arrays.toString(args));
                return null;
            }
        }).when(mockRequestDispatcher).forward(null, null);

        HttpSession mockSession = mock(HttpSession.class);
        when(mockSession.getAttribute(USER_ATTRIBUTE_NAME)).thenReturn(new Admin());

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getParameter(COMMAND_PARAMETER_NAME)).thenReturn(COMMAND_TO_EXECUTE_NAME);
        when(mockRequest.getSession(true)).thenReturn(mockSession);
        when(mockRequest.getRequestDispatcher(Paths.PAGE_NO_PAGE)).thenReturn(
                mockRequestDispatcher);

        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                System.out.println(Arrays.toString(args));
                return null;
            }
        }).when(mockResponse).sendRedirect(REDIRECT_TO);

        Controller controller = new Controller();
        controller.doGet(mockRequest, mockResponse);
        controller.doPost(mockRequest, mockResponse);
    }

}
