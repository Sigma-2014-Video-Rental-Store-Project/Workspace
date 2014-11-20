package ua.nure.sigma.store.dao.postgresql;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class PostgreSqlFilmDAOTest {
    Connection connection;
    Film film = mock(Film.class);
    List<Category> categories = new ArrayList<Category>();
    FilmDAO dao = DAOFactory.getInstance().getFilmDAO();

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(DAOFactory.class);
        connection = mock(Connection.class, RETURNS_DEEP_STUBS);
        when(DAOFactory.getConnection()).thenReturn(connection);
        when(film.getAmount()).thenReturn(10);
        when(film.getBonusForRent()).thenReturn(1000L);
        when(film.getCopiesLeft()).thenReturn(9);
        when(film.getCover()).thenReturn("0.jpj");
        when(film.getDescription()).thenReturn("test");
        when(film.getFilmId()).thenReturn(1);
        when(film.getGeneralPrice()).thenReturn(2000L);
        when(film.getRentPrice()).thenReturn(100L);
        when(film.getTitle()).thenReturn("title");
        when(film.getYear()).thenReturn(2010);

    }

    @Test
    public void testFindAllFilms() throws Exception {
        ResultSet rs = connection.createStatement().executeQuery(FilmSqlQuery.SQL_SELECT_FROM_FILMS_ALL_FILM);
        when(rs.next()).thenReturn(true, true,false);
        assertThat(dao.findAllFilms().size(),is(2));

    }
    @Test
    public void testFindFilmById() throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(FilmSqlQuery.SQL_SELECT_FROM_FILM_BY_ID);
        preparedStatement.setInt(1,1);
        ResultSet rs = preparedStatement.executeQuery();
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("ID")).thenReturn(1);
        when(rs.getString("TITLE")).thenReturn("test");
        when(rs.getInt("YEAR")).thenReturn(2010);
        when(rs.getString("DESCRIPTION")).thenReturn("desk");
        when(rs.getString("COVER")).thenReturn("0.jpg");
        when(rs.getInt("AMOUNT")).thenReturn(10);
        when(rs.getLong("GENERAL_PRICE")).thenReturn(1000L);
        when(rs.getLong("RENT_PRICE")).thenReturn(200L);
        when(rs.getLong("BONUS_FOR_RENT")).thenReturn(10L);
        when( rs.getInt("rentedCp")).thenReturn(1);
        Film film = dao.findFilmById(1);
        assertThat(film.getTitle(),is("test"));
        assertThat(film.getYear(),is(2010));
        assertThat(film.getDescription(),is("desk"));
        assertThat(film.getAmount(),is(10));
        assertThat(film.getGeneralPrice(),is(1000L));
        assertThat(film.getRentPrice(),is(200L));
        assertThat(film.getBonusForRent(),is(10L));
        assertThat(film.getCopiesLeft(),is(9));
    }

    @Test
    public void testCreateFilm() throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(FilmSqlQuery.SQL_INSERT_INTO_FILMS);
        ResultSet rs = preparedStatement.executeQuery();
        when(rs.next()).thenReturn(true,false);
        when(rs.getInt(1)).thenReturn(1);
        Film film = new Film();
        dao.createFilm(film, connection);
        assertThat(film.getFilmId(),is(1));
    }

    @Test
    public void testCreateFilmWithCategories() throws Exception {
        //Film film = new Film();
        PreparedStatement preparedStatement = connection.prepareStatement(FilmSqlQuery.SQL_INSERT_INTO_FILMS);
        categories.add(new Category());
        dao.createFilmWithCategories(film,categories);
        verify(preparedStatement).executeQuery();
        categories.remove(0);
    }

    @Test
    public void testUpdateFilm() throws Exception {
       // Film film = new Film();
        PreparedStatement preparedStatement = connection.prepareStatement(FilmSqlQuery.SQL_UPDATE_FILM);
        dao.updateFilm(film);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteFilm() throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(FilmSqlQuery.SQL_DELETE_FILM);
        dao.deleteFilm(film.getFilmId());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testExeption() throws Exception {
        when(connection.createStatement().executeQuery(FilmSqlQuery.SQL_SELECT_FROM_FILMS_ALL_FILM)).thenThrow(SQLException.class);
        dao.findAllFilms();
    }
    @Test
    public void testExeptionById() throws Exception {
        when(connection.prepareStatement(FilmSqlQuery.SQL_SELECT_FROM_FILM_BY_ID).executeQuery()).thenThrow(SQLException.class);
        dao.findFilmById(film.getFilmId());
    }
    @Test
    public void testExeptionCreateFilmWithCategory() throws Exception {
        when(connection.prepareStatement(FilmSqlQuery.SQL_INSERT_INTO_FILMS).executeQuery()).thenThrow(SQLException.class);
        dao.createFilmWithCategories(film, categories);
    }
    @Test
    public void testExeptionUpdate() throws Exception {
        when(connection.prepareStatement(FilmSqlQuery.SQL_UPDATE_FILM).executeUpdate()).thenThrow(SQLException.class);
        dao.findFilmById(film.getFilmId());
    }
    @Test
    public void testExeptionDelete() throws Exception {
        when(connection.prepareStatement(FilmSqlQuery.SQL_DELETE_FILM).executeUpdate()).thenThrow(SQLException.class);
        dao.createFilmWithCategories(film,categories);
    }
}