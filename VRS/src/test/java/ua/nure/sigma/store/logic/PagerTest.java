package ua.nure.sigma.store.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 10.10.14.
 */
public class PagerTest {

    private List<Integer> originModel;
    private Pager pager;
    private static final int RECORDS_ON_PAGE = 2;

    @Before
    public void createModel(){
        originModel = new ArrayList<Integer>();
        originModel.add(1);
        originModel.add(2);
        originModel.add(3);
        originModel.add(4);
        originModel.add(5);
        pager = new Pager(originModel);
        pager.setRecordCountOnPage(RECORDS_ON_PAGE);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBeginnig() {
        List<Integer> model = (List<Integer>) pager.getModel();
        assertEquals("Wrong count of totoal records", 5, pager.getRecordsTotal());
        assertEquals("Wrong count of records on page from Pager.", 2, model.size());
        assertEquals("Wrong first element in the first page.", new Integer(1), model.get(0));
        assertEquals("Wrong second element in the first page.", new Integer(2), model.get(1));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testChangingPage(){
        pager.next();
        List<Integer> model = (List<Integer>) pager.getModel();
        assertEquals("Wrong first element in the second page after changing index.", new Integer(3), model.get(0));
        assertEquals("Wrong second element in the second page after changing index.", new Integer(4), model.get(1));
        pager.prev();
        assertEquals("Prev function doesn't work", 1, pager.getPageIndex());
    }

    @Test
    public void testCountOfPages(){
        assertEquals("Wrong count of pages", 3, pager.getPages());
    }

    @Test
    public void testLastPage(){
        pager.setPageIndex(3);
        assertEquals("SetPage doesn't work.", 3, pager.getPageIndex());
        assertEquals("Last page is wrong", new Integer(5), pager.getModel().get(0));
    }

    @Test
    public void testSettingRecordCount(){
        pager.setRecordCountOnPage(4);
        assertEquals("Wrong setting count of records on the page.", 4, pager.getRecords());
        assertEquals("Wrong page creating after changing count of records.", 2, pager.getPages());
        pager.setRecordCountOnPage(2);
    }

    @Test
    public void testCreatingEmptyPager(){
        Pager emptyPager = new Pager(new ArrayList<Integer>());
        assertEquals("Error while creating empty pager.", 1, emptyPager.getPages());
    }
}
