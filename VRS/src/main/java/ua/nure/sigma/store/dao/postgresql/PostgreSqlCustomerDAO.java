package ua.nure.sigma.store.dao.postgresql;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.CustomerDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.entity.Sex;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PostgreSqlCustomerDAO implements CustomerDAO, CustomerSqlQuery{


    private static final String SQL_DEBTOR = "SELECT FILM_ID FROM FILM_AT_RENT, RENT WHERE RENT.CUSTOMER_ID = ? AND FILM_AT_RENT.RENT_ID = RENT.RENT_ID AND FILM_AT_RENT.accepted_date is not null";
    private static final Logger LOG = Logger
            .getLogger(PostgreSqlCustomerDAO.class);

    private Customer extractCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerID(rs.getInt(CUSTOMER_ID_PARAM));
        customer.setLastName(rs.getString(LAST_NAME_PARAM));
        customer.setFirstName(rs.getString(FIRST_NAME_PARAM));
        customer.setMiddleName(rs.getString(MIDLE_NAME_PARAM));
        customer.setCustomerEmail(rs.getString(EMAIL_PARAM));
        customer.setCustomerPhone( rs.getString(PHONE_PARAM));
        customer.setSexID(rs.getInt(SEX_ID_PARAM));
        customer.setCustomerPhoto(rs.getString(PHOTO_PARAM));
        try {
            customer.addBonus(rs.getLong(BONUS_PARAM));
        } catch (NotEnoughOfBonusExeption notEnoughOfBonusExeption) {
        }
        return customer;
    }

    int setupPrepareStatement(PreparedStatement pstmnt, Customer customer, int position) throws SQLException {
        pstmnt.setString(position++, customer.getLastName());
        pstmnt.setString(position++, customer.getFirstName());
        pstmnt.setString(position++, customer.getMiddleName());
        pstmnt.setString(position++, customer.getCustomerEmail());
        pstmnt.setString(position++, customer.getCustomerPhone());
        pstmnt.setInt(position++, customer.getSexID());
        pstmnt.setString(position++, (customer.getCustomerPhoto() == null) ? "0.jpg" : customer.getCustomerPhoto());
        pstmnt.setLong(position++, customer.getBonus());
        return position;
    }

    @Override
    public void createCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_INSERT_INTO_CUSTOMERS);
            int position = 1;
            setupPrepareStatement(pstmnt,customer,position);
            rs = pstmnt.executeQuery();
            if (rs.next()){
                customer.setCustomerID(rs.getInt(1));
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
            LOG.error("Can not add new client.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customerList = new ArrayList<Customer>();
        Connection connection = null;
        Statement stmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(SQL_SELECT_FROM_CUSTOMERS_ALL_CUSTOMER);
            while (rs.next()) {
                customerList.add(extractCustomer(rs));
            }
        } catch (Exception e) {
            LOG.error("Can not obtain Customers.", e);
        } finally {
            DAOFactory.close(stmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return customerList;
    }


    @Override
    public Customer findCustomerByID(int id) {
        Connection connection = null;
        Customer customer = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            customer = findCustomerByID(connection,id);
        } catch (Exception e) {//
            LOG.error("Can not obtain Customer by id.", e);
        } finally {
            DAOFactory.commitAndClose(connection);

        }
        return customer;
    }

    @Override
    public Customer findCustomerByID(Connection connection, int id) {
        Customer customer = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_CUSTOMERS_BY_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                customer = extractCustomer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
        }
        return customer;
    }

    @Override
    public void deleteCustomer(Customer customer) throws Exception {
        deleteCustomerByID(customer.getCustomerID());
    }

    @Override
    public void deleteCustomerByID(int id) throws Exception {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            if (checkDebtor(connection,id)) throw new Exception();
            pstmnt = connection.prepareStatement(SQL_DELETE_CUSTOMER);
            int position = 1;
            pstmnt.setInt(position, id);
            pstmnt.execute();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
            LOG.error("Can not delete Customer's block.", e);
            throw e;
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }

    }

    private boolean checkDebtor(Connection connection, int customerId){
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean b = false;
        try {
            pstm = connection.prepareStatement(SQL_DEBTOR);
            pstm.setInt(1,customerId);
            rs = pstm.executeQuery();
            if (rs.next())  b = true;
        }catch (Exception e){
            return false;
        }finally {
            DAOFactory.close(pstm);
        }
        return b;
    }

    @Override
    public void updateCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_UPDATE_CUSTOMERS);
            int position = 1;
            position = setupPrepareStatement(pstmnt,customer,position);
            pstmnt.setInt(position, customer.getCustomerID());
            pstmnt.executeUpdate();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
           LOG.error("Can not update Customer's block.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }

    }
}
