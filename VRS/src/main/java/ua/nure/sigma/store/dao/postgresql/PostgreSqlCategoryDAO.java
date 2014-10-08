package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.CategoryDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PostgreSqlCategoryDAO implements CategoryDAO{
    private static final String SQL_SELECT_FROM_CATEGORIES = "SELECT * FROM CATEGORIES";
    private static final String SQL_SELECT_FROM_CATEGORIES_BY_ID = "SELECT * FROM CATEGORIES WHERE CATEGORY_ID = ?";
    private static final String SQL_SELECT_FROM_CATEGORIES_BY_NAME =
            "SELECT CATEGORY_ID FROM CATEGORIES WHERE CATEGORY_NAME = ?";

    @Override
    public int findCategoryIdByName(String name) {
        int id = 0;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection
                    .prepareStatement(SQL_SELECT_FROM_CATEGORIES_BY_NAME);
            pstmnt.setString(1, name);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("CATEGORY_ID");
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not find Category id by name.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return id;
    }

    /**
     * Extracts Category bean from ResultSet object.
     *
     * @param rs
     *            ResultSet object after query execution.
     * @return fulfilled Category bean.
     * @throws SQLException
     *             caused by troubles connected with field extraction.
     */
    private Category extractCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("CATEGORY_ID"));
        category.setName(rs.getString("CATEGORY_NAME"));

        return category;
    }

    @Override
    public List<Category> findAllCategory() {
        List<Category> categoryList = new ArrayList<Category>();
        Connection connection = null;
        Statement stmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(SQL_SELECT_FROM_CATEGORIES);
            while (rs.next()) {
                categoryList.add(extractCategory(rs));
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not find all Categories.", e);
        } finally {
            DAOFactory.close(stmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return categoryList;
    }

    @Override
    public Category findCategoryByID(int id) {
        Category category = null;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_CATEGORIES_BY_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                category = extractCategory(rs);
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not find Category by id.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return category;
    }
}
