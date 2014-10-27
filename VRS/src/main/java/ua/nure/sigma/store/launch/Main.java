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

//        Rent rent = new Rent();
//        rent.setCustomerID(1);
//        rent.setRentDate(new Date());
//        List<FilmForRent> forRents = new ArrayList<FilmForRent>();
//        FilmForRent filmForRent =  new FilmForRent();
//        filmForRent.setCopies(2);
//        filmForRent.setFilmID(13);
//        Date dt = new Date();
//        Calendar c = Calendar.getInstance();
//        c.setTime(dt);
//        c.add(Calendar.DATE, 3);
//        dt = c.getTime();
//        filmForRent.setFutureDate(dt);
//        forRents.add(filmForRent);
//        FilmForRent f2 = new FilmForRent();
//        f2.setCopies(3);
//        f2.setFilmID(17);
//        Date dt1 = new Date();
//        c = Calendar.getInstance();
//        c.setTime(dt1);
//        c.add(Calendar.DATE, 2);
//        dt1 = c.getTime();
//        f2.setFutureDate(dt1);
//        forRents.add(f2);
//        rent.setFilmList(forRents);
//        DAOFactory.getInstance().getRentDAO().createRent(rent);

//        List<Category> categories = DAOFactory.getInstance().getFilmCategoryDAO().findCategoriesByFilmID(10);

        Film film = new Film();
        film.setGeneralPrice(2000);
        film.setCover("25.jpg");
        film.setAmount(12);
        film.setYear(2015);
        film.setRentPrice(1984);
        film.setBonusForRent(850);
        film.setTitle("FxF");
        film.setDescription("FxF is comming..");
        List<Category> categories = new ArrayList<Category>();
        Category category = new Category();
        category.setId(1);
        categories.add(category);
        FilmDAO dao = DAOFactory.getInstance().getFilmDAO();
        //dao.createFilmWithCategories(film,categories);
        //Film film = DAOFactory.getInstance().getFilmDAO().findFilmById(10);
        dao.createFilm(film);
        int i = dao.getLastID();
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
