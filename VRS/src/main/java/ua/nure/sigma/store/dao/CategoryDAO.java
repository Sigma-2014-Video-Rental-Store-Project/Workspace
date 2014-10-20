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
    Category findCategoryByID(int id);
    Category findCategoryByID(Connection connection , int id);
//    Category findCategoryByID(Connection connection, int id)throws SQLException;
    int findCategoryIdByName(String name);

}
