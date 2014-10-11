package ua.nure.sigma.store.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Context listener.
 *
 * @author Denis Shevchenko
 * @version 1.0
 */
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log("Servlet context initialization starts");

        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);

        log("Servlet context initialization finished");
    }

    /**
     * Initializes Log4j framework.
     *
     * @param servletContext
     */
    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext
                    .getRealPath("WEB-INF/log4j.properties"));
        } catch (Exception ex) {
            LOG.error("Can not configure Log4j", ex);
        }
        log("Log4J initialization finished");
        LOG.debug("Log4j has been initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        log("Servlet context destruction starts");
        log("Servlet context destruction finished");
    }

    /**
     * Writes logs before Log4J is initialized.
     *
     * @param message that will be logged.
     */
    private void log(String message) {
        System.out.println("[ContextListener] " + message);
    }
}