package ua.nure.sigma.store.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ua.nure.sigma.store.entity.*;
/**
 * Created by nikolaienko on 07.10.14.
 */
public interface AdminDAO {
    /*
     * Gets User bean found by 'login' argument or 'null' if it was not found.
     *
     * @param login
     *            of tariff that will be searched.
     * @return formed bean.
     */
    Admin findUserByLogin(String login);

    /*
     * Gets User bean found by 'id' argument or 'null' if it was not found.
     *
     * @param id
     *            of tariff that will be searched.
     * @return formed bean.
     */
    Admin findUserById(long id);

    /*
     * Gets list of User beans, that have roleId equals to 2 (excluding admin).
     * Default sorting options used.
     *
     * @return list of User beans.
     */
    List<Admin> findAllClients();

    /*
     * Gets list of User beans, that have roleId equals to 2 (excluding admin).
     * Default sorting options used. Do not closes connection, do not commits
     * transaction and do not rollbacks if something goes wrong.
     *
     * @param connection
     *            used for transaction.
     * @return list of User beans.
     * @throws SQLException
     *             that indicates need of rollback.
     */
    List<Admin> findAllAdmin(Connection connection) throws SQLException;



    /*
     * Adds new client in the DB.
     *
     * @param login
     *            of the new client.
     * @param password
     *            of the new client.
     * @param first
     *            of the new client.
     * @param last
     *            of the new client.
     * @param blockId
     *            of the new client.
     */
    void createAdmin(Admin admin);
    void updateAdmin(Admin admin);



}
