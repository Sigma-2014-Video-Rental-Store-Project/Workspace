package ua.nure.sigma.store.web.command.filmlist;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ua.nure.sigma.store.dao.CategoryDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmCategoryDAO;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.web.list.Categories;

/**
 * @author Sergey Laposhko
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class FilmListCategoryCommandTest {

    /**
     * Test method for
     * {@link ua.nure.sigma.store.web.command.filmlist.FilmListCategoryCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     * .
     * @throws ServletException 
     * @throws IOException 
     */
    @Test
    public final void testExecute() throws IOException, ServletException {
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	HttpSession session = mock(HttpSession.class);

	Admin admin = mock(Admin.class);

	List<Category> categories = new ArrayList<Category>();
	Category cat1 = new Category();
	cat1.setId(1);
	cat1.setName("1");
	categories.add(cat1);

	CategoryDAO categoryDAO = mock(CategoryDAO.class);
	when(categoryDAO.findAllCategory(Mockito.anyInt())).thenReturn(categories);
	
	FilmCategoryDAO filmCategoryDAO = mock(FilmCategoryDAO.class);

	DAOFactory daoFactory = mock(DAOFactory.class);
	when(daoFactory.getCategoryDAO()).thenReturn(categoryDAO);
	when(daoFactory.getFilmCategoryDAO()).thenReturn(filmCategoryDAO);

	PowerMock.mockStatic(DAOFactory.class);
	EasyMock.expect(DAOFactory.getInstance()).andReturn(daoFactory).anyTimes();
	PowerMock.replay(DAOFactory.class);
	
	
	when(request.getParameter(FilmListCommand.CATEGORIES_PARAM_NAME))
		.thenReturn("1");
	when(request.getSession()).thenReturn(session);
	when(session.getAttribute(FilmListCategoryCommand.USER_PARAM_NAME)).thenReturn(admin);

	new FilmListCategoryCommand().execute(request, response);
	
	verify(filmCategoryDAO).findFilmsByCategoryID(1);
	verify(session).setAttribute(
		Mockito.eq(FilmListCommand.CATEGORIES_PARAM_NAME),
		Mockito.argThat(new CategoriesMatcher(categories)));
    }

    private class CategoriesMatcher extends BaseMatcher<Categories> {

	private List<Category> list;
	
	/**
	 * Instantiates a new categories matcher.
	 *
	 * @param cats the cats
	 */
	public CategoriesMatcher(List<Category> cats) {
	    list = cats;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hamcrest.Matcher#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(Object item) {
	    if (!(item instanceof Categories))
		return false;
	    Categories categories = (Categories) item;
	    return categories.getModel().equals(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo(Description description) {
	    description.appendText("CategoriesMatcher");
	}

    }

    /**
     * Test execute with out search.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    public final void testExecuteWithOutSearch() throws IOException, ServletException{
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	HttpSession session = mock(HttpSession.class);

	Admin admin = mock(Admin.class);

	List<Category> categories = new ArrayList<Category>();
	Category cat1 = new Category();
	cat1.setId(1);
	cat1.setName("1");
	categories.add(cat1);
	
	CategoryDAO categoryDAO = mock(CategoryDAO.class);
	when(categoryDAO.findAllCategory(Mockito.anyInt())).thenReturn(categories);
	
	FilmCategoryDAO filmCategoryDAO = mock(FilmCategoryDAO.class);

	DAOFactory daoFactory = mock(DAOFactory.class);
	when(daoFactory.getCategoryDAO()).thenReturn(categoryDAO);
	when(daoFactory.getFilmCategoryDAO()).thenReturn(filmCategoryDAO);

	PowerMock.mockStatic(DAOFactory.class);
	EasyMock.expect(DAOFactory.getInstance()).andReturn(daoFactory).anyTimes();
	PowerMock.replay(DAOFactory.class);
	
	
	when(request.getParameter(FilmListCommand.CATEGORIES_PARAM_NAME))
		.thenReturn(null);
	when(request.getSession()).thenReturn(session);
	when(session.getAttribute(FilmListCategoryCommand.USER_PARAM_NAME)).thenReturn(admin);

	new FilmListCategoryCommand().execute(request, response);
	
	verify(filmCategoryDAO).findFilmsByCategoryID(1);
	verify(session).setAttribute(
		Mockito.eq(FilmListCommand.CATEGORIES_PARAM_NAME),
		Mockito.argThat(new CategoriesMatcher(categories)));
    }
    
}
