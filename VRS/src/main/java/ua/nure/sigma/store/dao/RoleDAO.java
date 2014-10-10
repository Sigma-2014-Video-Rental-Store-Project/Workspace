package ua.nure.sigma.store.dao;

import ua.nure.sigma.store.entity.Role;

/**
 * Created by nikolaienko on 07.10.14.
 */
public interface RoleDAO {
    Role findRoleByID(int id);
    Role findRoleByName(String name);
}
