package ua.nure.sigma.store.dao.postgresql;

import static java.util.Arrays.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmCategoryDAO;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class PostgreSqlFilmCategoryDAOTest {
    Connection connection;
    Film film = mock(Film.class);
    Category category = mock(Category.class);
    FilmCategoryDAO dao;
    DAOFactory daoFactory = mock(PosgreSqlDAO.class);
    PostgreSqlFilmDAO filmDAO = mock(PostgreSqlFilmDAO.class);
    PostgreSqlCategoryDAO categoryDAO = mock(PostgreSqlCategoryDAO.class);
    @Before
    public void setUp() throws Exception {
        when(daoFactory.getFilmCategoryDAO()).thenCallRealMethod();
        when(daoFactory.getFilmDAO()).thenReturn(filmDAO);
        when(daoFactory.getCategoryDAO()).thenReturn(categoryDAO);
        when(filmDAO.findFilmById(connection, 1)).thenReturn(film);
        when(filmDAO.findFilmById(connection,2)).thenReturn(film);
        when(categoryDAO.findCategoryByID(connection, 1, 1)).thenReturn(category);
        when(categoryDAO.findCategoryByID(connection,2,1)).thenReturn(category);
        dao = DAOFactory.getInstance().getFilmCategoryDAO();
        connection = mock(Connection.class, RETURNS_DEEP_STUBS);
        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getConnection()).thenReturn(connection);
        when(DAOFactory.getInstance()).thenReturn(daoFactory);
        when(category.getId()).thenReturn(1);
        when(category.getName()).thenReturn("test");
    }

    @Test
    public void testFindFilmsByCategoryID() throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(FilmCategoriesSqlQuery.SQL_SELECT_FROM_FILM_CATEGORY_BY_CATEGORY_ID);
        preparedStatement.setInt(1,1);
        ResultSet rs = preparedStatement.executeQuery();
        when(rs.next()).thenReturn(true, true,false);
        List<Film> filmList = dao.findFilmsByCategoryID(1);
        assertThat(filmList.size(),is(2));
    }

    @Test
    public void testFindCategoriesByFilmID() throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(FilmCategoriesSqlQuery.SQL_SELECT_FROM_FILM_CATEGORIES_CATEGORIES_BY_FILM_ID);
        preparedStatement.setInt(1, 1);
        ResultSet rs = preparedStatement.executeQuery();
        when(rs.next()).thenReturn(true,true,false);
        when(rs.getInt(CategorySqlQuery.CATEGORY_ID_PARAM)).thenReturn(1);
        List<Category> categoryList = dao.findCategoriesByFilmID(1);
       assertEquals(2,categoryList.size());
    }


    @Test
    public void testCreateFilmCategory() throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(FilmCategoriesSqlQuery.SQL_INSERT_INTO_FILM_CATEGORIES);
        dao.createFilmCategories(film,asList(category));
        verify(preparedStatement).execute();
    }

    @Test
    public void testUpdateFilmCategories() throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(FilmCategoriesSqlQuery.SQL_INSERT_INTO_FILM_CATEGORIES);
        dao.updateFilmCategories(film, asList(category));
        verify(preparedStatement).execute();
    }

    @Test
    public void testDeleteFilmCategories() throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(FilmCategoriesSqlQuery.SQL_DELETE_FILM_CATEGORIES);
        dao.deleteFilmCategories(1);
        verify(preparedStatement).executeUpdate();
    }

}