package ua.nure.sigma.store.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ua.nure.sigma.store.entity.*;
/**
 * Created by nikolaienko on 07.10.14.
 */
public interface AdminDAO {

    Admin findAdminByLogin(String login);
    Admin findAdminById(int id);
    List<Admin> findAllAdmins();
    List<Admin> findAllAdmin(Connection connection) throws SQLException;
    void createAdmin(Admin admin);
    void updateAdminPassword(Admin admin);
    void deleteAdmin(Admin admin);



}
