package ua.nure.sigma.store.launch;
import java.io.File;
import java.text.NumberFormat;
import java.util.*;

import org.apache.catalina.startup.Tomcat;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.*;
import ua.nure.sigma.store.web.list.Categories;

public class Main {

    public static void main(String[] args) throws Exception {

        DAOFactory.getInstance().getSexDAO().findSexByID(1,1);
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
