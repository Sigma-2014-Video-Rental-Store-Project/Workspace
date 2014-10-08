package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.*;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PosgreSqlDAO extends DAOFactory {
    @Override
    public AdminDAO getAdminDAO() {
        return null;
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        return null;
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return null;
    }

    @Override
    public FilmDAO getFilmDAO() {
        return null;
    }

    @Override
    public RentDAO getRentDAO() {
        return null;
    }

    @Override
    public RoleDAO getRoleDAO() {
        return null;
    }

    @Override
    public FilmCategoryDAO getFilmCategoryDAO() {
        return null;
    }

    @Override
    public SexDAO getSexDAO() {
        return null;
    }
}
