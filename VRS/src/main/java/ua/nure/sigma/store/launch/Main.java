package ua.nure.sigma.store.launch;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.Tomcat;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.*;
import ua.nure.sigma.store.web.list.Categories;

public class Main {

    public static void main(String[] args) throws Exception {

        List<Customer> customers = DAOFactory.getInstance().getCustomerDAO().findAllCustomers();

        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();  
    }
}
