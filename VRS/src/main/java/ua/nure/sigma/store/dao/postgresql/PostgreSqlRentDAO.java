package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.*;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.entity.Rent;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PostgreSqlRentDAO implements RentDAO {

    private static final String SQL_SELECT_FROM_RENTS_CUSTOMER_BY_RENT_ID = "SELECT * FROM RENT WHERE RENT_ID = ?";
    private static final String SQL_SELECT_FROM_RENTS_RENTS__BY_CUSTOMER_ID = "SELECT * FROM RENT WHERE CUSTOMER_ID =?";
    private static final String SQL_INSERT_INTO_RENTS = "INSERT INTO RENT(RENT_ID, CUSTOMER_ID, RENTED_DATE)  VALUES(DEFAULT,?,CURRENT_DATE) RETURNING RENT_ID; ";
    private static final String SQL_UPDATE_RENTS=
            "UPDATE RENT SET  ACCEPTED_DATE = now() WHERE CUSTOMER_ID = ? AND FILM_ID = ?";
    private static final String SQL_UPDATE_RENT_FILM_COPY=
            "UPDATE RENT SET  COUNT = ? WHERE CUSTOMER_ID = ? AND FILM_ID = ?";

    private Rent extractRent(ResultSet rs, Connection connection) throws SQLException {
        Rent rent = new Rent();
        rent.setCustomerID(rs.getInt("CUSTOMER_ID"));
        rent.setRentID(rs.getInt("RENT_ID"));
        rent.setRentDate(rs.getDate("RENTED_DATE"));
        rent.setFilmList(DAOFactory.getInstance().getFilmRentedDAO().findFilmRentedByRentID(rent.getRentID()));
        return rent;
    }


    @Override
    public void createRent(Rent rent) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        long currentID = 0;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_INSERT_INTO_RENTS);
            int position = 1;
            pstmnt.setInt(position++,rent.getCustomerID());
            rs = pstmnt.executeQuery();
            if (rs.next()){
                currentID = rs.getLong(1);
            }
            DAOFactory.getInstance().getFilmRentedDAO().createFilmsRented(currentID, rent.getFilmList(),connection);
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not add new client.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

    @Override
    public Customer findCustomerByRentID(long rentID) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_RENTS_CUSTOMER_BY_RENT_ID);
            pstmnt.setLong(1, rentID);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                customer = DAOFactory.getInstance().getCustomerDAO().findCustomerByID(connection,rs.getInt("CUSTOMER_ID"));
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by id.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return customer;
    }

    @Override
    public List<Rent> findRentByCustomerID(int customerID) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        List<Rent> rentList = new ArrayList<Rent>();
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_RENTS_RENTS__BY_CUSTOMER_ID);
            pstmnt.setLong(1, customerID);
            rs = pstmnt.executeQuery();
            while (rs.next()) {
                rentList.add(extractRent(rs,connection));

            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by id.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return rentList;
    }

    @Override
    public void closeRent(long rentID) {
        DAOFactory.getInstance().getFilmRentedDAO().closeFilmRent(rentID);
    }

}
