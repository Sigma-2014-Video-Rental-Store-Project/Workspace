package ua.nure.sigma.store.web;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ControllerTest {

    @Test
    public void testController() throws ServletException, IOException {

        RequestDispatcher mockRequestDispatcher = mock(RequestDispatcher.class);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                System.out.println(Arrays.toString(args));
                return null;
            }
        }).when(mockRequestDispatcher).forward(null, null);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getParameter("command")).thenReturn("wrongCommand");
        when(mockRequest.getRequestDispatcher(Paths.PAGE_NO_PAGE)).thenReturn(
                mockRequestDispatcher);

        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                System.out.println(Arrays.toString(args));
                return null;
            }
        }).when(mockResponse).sendRedirect("hhh");

        Controller controller = new Controller();
        controller.doGet(mockRequest, mockResponse);
        controller.doPost(mockRequest, mockResponse);
    }

}
