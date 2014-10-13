package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.CustomerDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.dao.RentDAO;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.entity.Rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PostgreSqlRentDAO implements RentDAO {

    private static final String SQL_SELECT_FROM_RENTS_CUSTOMER_BY_RENT_ID = "SELECT * FROM RENT WHERE RENT_ID = ?";
    private static final String SQL_SELECT_FROM_RENTS_RENTS__BY_CUSTOMER_ID = "SELECT * FROM RENT WHERE CUSTOMER_ID =?";
    private static final String SQL_INSERT_INTO_RENTS = "INSERT INTO RENT VALUES(?,?,now(),?,NULL)";
    private static final String SQL_UPDATE_RENTS=
            "UPDATE RENT SET  ACCEPTED_DATE = now() WHERE CUSTOMER_ID = ? AND FILM_ID = ?";
    private static final String SQL_UPDATE_RENT_FILM_COPY=
            "UPDATE RENT SET  COUNT = ? WHERE CUSTOMER_ID = ? AND FILM_ID = ?";



    private Rent extractRent(Connection connection, ResultSet rs) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        Rent rent = new Rent();
        rent.setFilm(filmDAO.findFilmByID(connection,rs.getInt("FILM_ID")));
        rent.setCustomer(customerDAO.findCustomerByID(connection,rs.getInt("CUSTOMER_ID")));
        return rent;
    }

    @Override
    public void createRent(Film film, Customer customer, Date dueDate) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection.prepareStatement(SQL_INSERT_INTO_RENTS);
            int position = 1;
            pstmnt.setInt(position++, customer.getCustomerID());
            pstmnt.setInt(position++, film.getFilmId());
            pstmnt.setTimestamp(position++, new Timestamp(dueDate.getTime()));
            pstmnt.executeUpdate();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not add new client.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

    @Override
    public void updateRentFilmCopy(int filmID, int customer_id, int copies) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection.prepareStatement(SQL_UPDATE_RENTS);
            int position = 1;
            pstmnt.setInt(position++, copies);
            pstmnt.setInt(position++, customer_id);
            pstmnt.setInt(position++, filmID);
            pstmnt.executeUpdate();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not update User's block.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

    @Override
    public void updateRent(int filmID, int customer_id, int copies) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection.prepareStatement(SQL_UPDATE_RENTS);
            int position = 1;
            pstmnt.setInt(position++, customer_id);
            pstmnt.setInt(position++, filmID);
            pstmnt.executeUpdate();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not update User's block.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

    @Override
    public Customer findCustomerByRentID(long rentID) {
        return null;
    }

    @Override
    public List<Rent> findRentByCustomerID(int id) {
        return null;
    }

    @Override
    public int findCountOfRentedCopiesByFilmID(int film_id) {
        int count = 0;
        return count;
    }
}
