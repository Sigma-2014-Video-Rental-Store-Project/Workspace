package ua.nure.sigma.store.web.filter;

import org.junit.Test;
import ua.nure.sigma.store.entity.Admin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommandAccessFilterTest {

    @Test
    public void testInit() throws Exception {

        // Prepare mock to imitate web.xml parameter source.
        FilterConfig filterConfig = mock(FilterConfig.class);
        when(filterConfig.getInitParameter("admin")).thenReturn("adminCommand");
        when(filterConfig.getInitParameter("common")).thenReturn("commonCommand");
        when(filterConfig.getInitParameter("out-of-control")).thenReturn("outOfControlCommand");

        // Test target object.
        CommandAccessFilter commandAccessFilter = new CommandAccessFilter();
        commandAccessFilter.init(filterConfig);
    }

    @Test
    public void testDoFilterNullSession() throws Exception {

        // Prepare mock to imitate web.xml parameter source.
        FilterConfig filterConfig = mock(FilterConfig.class);
        when(filterConfig.getInitParameter("admin")).thenReturn("adminCommand");
        when(filterConfig.getInitParameter("common")).thenReturn("commonCommand");
        when(filterConfig.getInitParameter("out-of-control")).thenReturn("outOfControlCommand");

        // Prepare filter for testing.
        CommandAccessFilter commandAccessFilter = new CommandAccessFilter();
        commandAccessFilter.init(filterConfig);

        // Create specific method mocks.
        RequestDispatcher requestDispatcherMock = mock(RequestDispatcher.class);
        HttpServletRequest servletRequestMock = mock(HttpServletRequest.class);
        when(servletRequestMock.getRequestDispatcher(anyString())).thenReturn(requestDispatcherMock);
        when(servletRequestMock.getParameter("command")).thenReturn(null);
        when(servletRequestMock.getSession(false)).thenReturn(null);
        ServletResponse servletResponseMock = mock(ServletResponse.class);
        FilterChain filterChainMock = mock(FilterChain.class);

        // Test target object.
        commandAccessFilter.doFilter(servletRequestMock, servletResponseMock, filterChainMock);
        verify(filterChainMock).doFilter(servletRequestMock, servletResponseMock);
    }

    @Test
    public void testDoFilterNullUser() throws Exception {

        // Prepare mock to imitate web.xml parameter source.
        FilterConfig filterConfig = mock(FilterConfig.class);
        when(filterConfig.getInitParameter("admin")).thenReturn("adminCommand");
        when(filterConfig.getInitParameter("common")).thenReturn("commonCommand");
        when(filterConfig.getInitParameter("out-of-control")).thenReturn("outOfControlCommand");

        // Prepare filter for testing.
        CommandAccessFilter commandAccessFilter = new CommandAccessFilter();
        commandAccessFilter.init(filterConfig);

        // Create specific method mocks.
        RequestDispatcher requestDispatcherMock = mock(RequestDispatcher.class);
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute("user")).thenReturn(null);
        HttpServletRequest servletRequestMock = mock(HttpServletRequest.class);
        when(servletRequestMock.getRequestDispatcher(anyString())).thenReturn(requestDispatcherMock);
        when(servletRequestMock.getParameter("command")).thenReturn(null);
        when(servletRequestMock.getSession(false)).thenReturn(sessionMock);
        ServletResponse servletResponseMock = mock(ServletResponse.class);
        FilterChain filterChainMock = mock(FilterChain.class);

        // Test target object.
        commandAccessFilter.doFilter(servletRequestMock, servletResponseMock, filterChainMock);
        verify(filterChainMock).doFilter(servletRequestMock, servletResponseMock);
    }

    @Test
    public void testDoFilterOutOfControlCommand() throws Exception {
        final String commandInRequest = "outOfControlCommand";

        // Prepare mock to imitate web.xml parameter source.
        FilterConfig filterConfig = mock(FilterConfig.class);
        when(filterConfig.getInitParameter("admin")).thenReturn("adminCommand");
        when(filterConfig.getInitParameter("common")).thenReturn("commonCommand");
        when(filterConfig.getInitParameter("out-of-control")).thenReturn(commandInRequest);

        // Prepare filter for testing.
        CommandAccessFilter commandAccessFilter = new CommandAccessFilter();
        commandAccessFilter.init(filterConfig);

        // Create specific method mocks.
        RequestDispatcher requestDispatcherMock = mock(RequestDispatcher.class);
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute("user")).thenReturn(new Admin());
        HttpServletRequest servletRequestMock = mock(HttpServletRequest.class);
        when(servletRequestMock.getRequestDispatcher(anyString())).thenReturn(requestDispatcherMock);
        when(servletRequestMock.getParameter("command")).thenReturn(commandInRequest);
        when(servletRequestMock.getSession(false)).thenReturn(sessionMock);
        ServletResponse servletResponseMock = mock(ServletResponse.class);
        FilterChain filterChainMock = mock(FilterChain.class);

        // Test target object.
        commandAccessFilter.doFilter(servletRequestMock, servletResponseMock, filterChainMock);
        verify(requestDispatcherMock).forward(servletRequestMock, servletResponseMock);
    }

    @Test
    public void testDoFilterAdminCommand() throws Exception {
        final String commandInRequest = "adminCommand";

        // Prepare mock to imitate web.xml parameter source.
        FilterConfig filterConfig = mock(FilterConfig.class);
        when(filterConfig.getInitParameter("admin")).thenReturn(commandInRequest);
        when(filterConfig.getInitParameter("common")).thenReturn("commonCommand");
        when(filterConfig.getInitParameter("out-of-control")).thenReturn("outOfControlCommand");

        // Prepare filter for testing.
        CommandAccessFilter commandAccessFilter = new CommandAccessFilter();
        commandAccessFilter.init(filterConfig);

        // Create specific method mocks.
        RequestDispatcher requestDispatcherMock = mock(RequestDispatcher.class);
        HttpSession sessionMock = mock(HttpSession.class);
        Admin admin = new Admin();
        admin.setRoleId(1);
        when(sessionMock.getAttribute("user")).thenReturn(admin);
        HttpServletRequest servletRequestMock = mock(HttpServletRequest.class);
        when(servletRequestMock.getRequestDispatcher(anyString())).thenReturn(requestDispatcherMock);
        when(servletRequestMock.getParameter("command")).thenReturn(commandInRequest);
        when(servletRequestMock.getSession(false)).thenReturn(sessionMock);
        ServletResponse servletResponseMock = mock(ServletResponse.class);
        FilterChain filterChainMock = mock(FilterChain.class);

        // Test target object.
        commandAccessFilter.doFilter(servletRequestMock, servletResponseMock, filterChainMock);
        verify(filterChainMock).doFilter(servletRequestMock, servletResponseMock);
    }

    @Test
    public void testDoFilterCommonCommand() throws Exception {
        final String commandInRequest = "commonCommand";

        // Prepare mock to imitate web.xml parameter source.
        FilterConfig filterConfig = mock(FilterConfig.class);
        when(filterConfig.getInitParameter("admin")).thenReturn("adminCommand");
        when(filterConfig.getInitParameter("common")).thenReturn(commandInRequest);
        when(filterConfig.getInitParameter("out-of-control")).thenReturn("outOfControlCommand");

        // Prepare filter for testing.
        CommandAccessFilter commandAccessFilter = new CommandAccessFilter();
        commandAccessFilter.init(filterConfig);

        // Create specific method mocks.
        RequestDispatcher requestDispatcherMock = mock(RequestDispatcher.class);
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute("user")).thenReturn(new Admin());
        HttpServletRequest servletRequestMock = mock(HttpServletRequest.class);
        when(servletRequestMock.getRequestDispatcher(anyString())).thenReturn(requestDispatcherMock);
        when(servletRequestMock.getParameter("command")).thenReturn(commandInRequest);
        when(servletRequestMock.getSession(false)).thenReturn(sessionMock);
        ServletResponse servletResponseMock = mock(ServletResponse.class);
        FilterChain filterChainMock = mock(FilterChain.class);

        // Test target object.
        commandAccessFilter.doFilter(servletRequestMock, servletResponseMock, filterChainMock);
        verify(filterChainMock).doFilter(servletRequestMock, servletResponseMock);
    }

    @Test
    public void testDoFilterNullCommand() throws Exception {

        // Prepare mock to imitate web.xml parameter source.
        FilterConfig filterConfig = mock(FilterConfig.class);
        when(filterConfig.getInitParameter("admin")).thenReturn("adminCommand");
        when(filterConfig.getInitParameter("common")).thenReturn("commonCommand");
        when(filterConfig.getInitParameter("out-of-control")).thenReturn("outOfControlCommand");

        // Prepare filter for testing.
        CommandAccessFilter commandAccessFilter = new CommandAccessFilter();
        commandAccessFilter.init(filterConfig);

        // Create specific method mocks.
        RequestDispatcher requestDispatcherMock = mock(RequestDispatcher.class);
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute("user")).thenReturn(new Admin());
        HttpServletRequest servletRequestMock = mock(HttpServletRequest.class);
        when(servletRequestMock.getRequestDispatcher(anyString())).thenReturn(requestDispatcherMock);
        when(servletRequestMock.getParameter("command")).thenReturn(null);
        when(servletRequestMock.getSession(false)).thenReturn(sessionMock);
        ServletResponse servletResponseMock = mock(ServletResponse.class);
        FilterChain filterChainMock = mock(FilterChain.class);

        // Test target object.
        commandAccessFilter.doFilter(servletRequestMock, servletResponseMock, filterChainMock);
        verify(requestDispatcherMock).forward(servletRequestMock, servletResponseMock);
    }

    @Test
    public void testDoFilterBlockRequest() throws Exception {
        final String commandInRequest = "noSuchCommand";

        // Prepare mock to imitate web.xml parameter source.
        FilterConfig filterConfig = mock(FilterConfig.class);
        when(filterConfig.getInitParameter("admin")).thenReturn("adminCommand");
        when(filterConfig.getInitParameter("common")).thenReturn("commonCommand");
        when(filterConfig.getInitParameter("out-of-control")).thenReturn("outOfControlCommand");

        // Prepare filter for testing.
        CommandAccessFilter commandAccessFilter = new CommandAccessFilter();
        commandAccessFilter.init(filterConfig);

        // Create specific method mocks.
        RequestDispatcher requestDispatcherMock = mock(RequestDispatcher.class);
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute("user")).thenReturn(new Admin());
        HttpServletRequest servletRequestMock = mock(HttpServletRequest.class);
        when(servletRequestMock.getRequestDispatcher(anyString())).thenReturn(requestDispatcherMock);
        when(servletRequestMock.getParameter("command")).thenReturn(commandInRequest);
        when(servletRequestMock.getSession(false)).thenReturn(sessionMock);
        ServletResponse servletResponseMock = mock(ServletResponse.class);
        FilterChain filterChainMock = mock(FilterChain.class);

        // Test target object.
        commandAccessFilter.doFilter(servletRequestMock, servletResponseMock, filterChainMock);
        verify(requestDispatcherMock).forward(servletRequestMock, servletResponseMock);
    }

    @Test
    public void testDestroy() {
        CommandAccessFilter commandAccessFilter = new CommandAccessFilter();
        commandAccessFilter.destroy();
    }
}