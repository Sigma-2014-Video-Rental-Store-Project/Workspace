package ua.nure.sigma.store.dao;

import ua.nure.sigma.store.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public interface CategoryDAO {
    List<Category> findAllCategory();
    List<Category> findAllCategory(int local);
    Category findCategoryByID(int id,int locale);
    Category findCategoryByID(Connection connection , int id, int locale) throws Exception;
    Category findCategoryByID(int id);
    Category findCategoryByID(Connection connection , int id) throws Exception;
//    Category findCategoryByID(Connection connection, int id)throws SQLException;
    int findCategoryIdByName(String name);

}
