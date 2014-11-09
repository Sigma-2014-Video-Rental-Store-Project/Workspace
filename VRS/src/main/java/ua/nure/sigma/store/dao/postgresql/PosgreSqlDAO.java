package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.*;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PosgreSqlDAO extends DAOFactory {
    @Override
    public AdminDAO getAdminDAO() {
        return new PosgreSqlAdminDAO();
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        return new PostgreSqlCustomerDAO();
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return new PostgreSqlCategoryDAO();
    }

    @Override
    public FilmDAO getFilmDAO() {
        return new PostgreSqlFilmDAO();
    }

    @Override
    public RentDAO getRentDAO() {
        return new PostgreSqlRentDAO();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return new PostgreSqlRoleDAO();
    }

    @Override
    public FilmCategoryDAO getFilmCategoryDAO() {
        return new PostgreSqlFilmCategoryDAO();
    }

    @Override
    public SexDAO getSexDAO() {
        return new PostgreSqlSexDAO();
    }

    @Override
    public FilmRentedDAO getFilmRentedDAO() {
        return new PostgreSqlFimsRentedDAO();
    }

    @Override
    public LocaleDAO getLocaleDAO() {
        return new PostgreSqlLocaleDAO();
    }
}
