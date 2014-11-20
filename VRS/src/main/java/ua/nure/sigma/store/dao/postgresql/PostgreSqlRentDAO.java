package ua.nure.sigma.store.dao.postgresql;

import org.apache.log4j.Logger;
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
public class PostgreSqlRentDAO implements RentDAO, RentSqlQuery {

    private static final Logger LOG = Logger.getLogger(PostgreSqlRentDAO.class);
    private Rent extractRent(ResultSet rs, Connection connection) throws SQLException {
        Rent rent = new Rent();
        rent.setCustomerID(rs.getInt(CUSTOMER_ID_PARAM));
        rent.setRentID(rs.getInt(RENT_ID_PARAM));
        rent.setRentDate(rs.getDate(RENTED_DATE_PARAM));
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
                rent.setRentID(rs.getLong(RENT_ID_PARAM));
                rent.setRentDate(rs.getDate(RENTED_DATE_PARAM));
            }
            DAOFactory.getInstance().getFilmRentedDAO().createFilmsRented(rent.getRentID(), rent.getFilmList(),connection);

        } catch (Exception e) {
            DAOFactory.rollback(connection);
            LOG.error("Can not create rent.", e);
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
            LOG.error("Can not obtain Customer by rentId.", e);
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
            LOG.error("Can not obtain all order by customer id.", e);
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
