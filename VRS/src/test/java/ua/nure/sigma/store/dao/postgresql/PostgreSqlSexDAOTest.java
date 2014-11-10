package ua.nure.sigma.store.dao.postgresql;

import org.junit.Before;
import org.junit.Test;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.SexDAO;

import static org.junit.Assert.*;

public class PostgreSqlSexDAOTest {

    static SexDAO dao;
    @Before
    public void setUp() throws Exception {
        dao = DAOFactory.getInstance().getSexDAO();
    }

}