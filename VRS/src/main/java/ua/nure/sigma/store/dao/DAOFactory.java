package ua.nure.sigma.store.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

/**
 * Created by nikolaienko on 07.10.14.
 */
public abstract class DAOFactory {

    private Connection connection;
    private static DAOFactory INSTANCE = null;
    public static final String DAO_FACTORY_CLASS_NAME =
            "ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO";

    public static synchronized DAOFactory getInstance() {
        if (INSTANCE == null) {
            try {
               // Class.forName(DRIVER_CLASS_NAME);
                Class<?> clazz = Class.forName(DAO_FACTORY_CLASS_NAME);
                INSTANCE = (DAOFactory) clazz.newInstance();
                //LOG.debug("DAO factory instance initialized.");
            } catch (Exception ex) {
                //LOG.error("Can't get instance of DAO factory.", ex);
            }
        }
        return INSTANCE;
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public abstract AdminDAO getAdminDAO();
    public abstract CustomerDAO getCustomerDAO();
    public abstract CategoryDAO getCategoryDAO();
    public abstract FilmDAO getFilmDAO();
    public abstract RentDAO getRentDAO();
    public abstract RoleDAO getRoleDAO();
    public abstract FilmCategoryDAO getFilmCategoryDAO();
    public abstract SexDAO getSexDAO();

}
