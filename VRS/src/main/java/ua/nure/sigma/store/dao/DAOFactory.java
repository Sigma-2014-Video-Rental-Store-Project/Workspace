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

    public static Connection getConnection() throws URISyntaxException, SQLException {
//        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        URI dbUri = new URI("postgres://luiqbfalbzbmva:zejlOFp6cr_-OZ2WXSKY3Owy9r@ec2-54-225-239-184.compute-1.amazonaws.com:5432/dadh22f44alqo");

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
    public static void commitAndClose(Connection connection) {
//        LOG.trace("Commit and close operation starts.");
        if (connection != null) {
            try {
                connection.commit();
//                LOG.trace("Commit finished.");
                connection.close();
//                LOG.trace("Connection closing finished.");
            } catch (SQLException ex) {
//                LOG.error("Can not commit transaction and close connection.",
//                        ex);
            }
        }
    }

    /**
     * Closes ResultSet given as an argument.
     *
     * @param rs
     *            ResultSet to be closed.
     */
    public static void close(ResultSet rs) {
//        LOG.trace("ResultSet closing starts.");
        if (rs != null) {
            try {
                rs.close();
//                LOG.trace("ResultSet closing finished.");
            } catch (SQLException ex) {
//                LOG.error("Can not close a result set.", ex);
            }
        }
    }

    /**
     * Closes Statement given as an argument.
     *
     * @param statement
     *            Statement to be closed.
     */
    public static void close(Statement statement) {
        //LOG.trace("Statement closing starts.");
        if (statement != null) {
            try {
                statement.close();
          //      LOG.trace("Statement closing finished.");
            } catch (SQLException ex) {
//                LOG.error("Can not close a statement.", ex);
            }
        }
    }

    /**
     * Rollbacks the given connection.
     *
     * @param connection
     *            Connection to be rollbacked and closed.
     */
    public static void rollback(Connection connection) {
        if (connection != null) {
//            LOG.trace("Connection rollback starts.");
            try {
                connection.rollback();
//                LOG.trace("Connection rollback finished.");
            } catch (SQLException ex) {
//                LOG.error("Can not rollback transaction.", ex);
            }
        }
    }
}
