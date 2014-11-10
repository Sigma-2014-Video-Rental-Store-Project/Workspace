package ua.nure.sigma.store.dao.postgresql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.nure.sigma.store.dao.CustomerDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;

import static org.junit.Assert.*;

public class PostgreSqlCustomerDAOTest {

    static CustomerDAO dao;
    static Customer customer;
    @Before
    public void setUp() throws Exception {
        dao = DAOFactory.getInstance().getCustomerDAO();
        customer = new Customer();
        customer.setCustomerEmail("test@test.com");
        customer.setCustomerPhone("+1000test");
        customer.setCustomerPhoto("0.jpg");
        customer.setFirstName("TestNAme");
        customer.setLastName("last");
        customer.setMiddleName("midle");
        customer.setSexID(1);
        dao.createCustomer(customer);
    }

    @After
    public void tearDown() throws Exception {
        dao.updateCustomer(customer);
        dao.deleteCustomer(customer);
    }

    @Test
    public void testFindAllCustomers() throws Exception {
        assertNotEquals(dao.findAllCustomers().size(),0);
    }

    @Test
    public void testFindAllDebtors() throws Exception {
        assertNotNull(dao.findAllCustomers());
    }

    @Test
    public void testFindCustomerByID() throws Exception {
        assertNotNull(dao.findCustomerByID(3));
    }


}