package ua.nure.sigma.store.logic;

import java.util.List;

/**
 * Created by Sergey Laposhko on 10.10.14.
 */
public class Pager {

    private static final int DEFAULT_RECORDS_NUMBER = 10;
    private static final int DEFAULT_PAGE_INDEX = 1;

    private int records;
    private int recordsTotal;
    private int pageIndex;
    private int pages;
    private List<?> origModel;
    private List<?> model;

    /**
     *
     * @param model list of objects to page
     */
    public Pager(List<?> model) {
        this.origModel = model;
        this.records = DEFAULT_RECORDS_NUMBER;
        this.pageIndex = DEFAULT_PAGE_INDEX;
        this.recordsTotal = model.size();
        updateCountOfPages();
        updateModel();
    }

    private void updateCountOfPages() {
        pages = records <= 0 ? 1 : recordsTotal / records;

        if (recordsTotal % records > 0) {
            pages++;
        }

        if (pages == 0) {
            pages = 1;
        }
    }

    /**
     * reloads paging.
     */
    public void updateModel() {
        int fromIndex = getFirst();
        int toIndex = getFirst() + records;

        if(toIndex > this.recordsTotal) {
            toIndex = this.recordsTotal;
        }

        this.model = origModel.subList(fromIndex, toIndex);
    }

    /**
     * Sets the next page.
     */
    public void next() {
        if(this.pageIndex < pages) {
            this.pageIndex++;
        }

        updateModel();
    }

    /**
     * Sets the prev page.
     */
    public void prev() {
        if(this.pageIndex > 1) {
            this.pageIndex--;
        }

        updateModel();
    }

    /**
     *
     * @return Max counts of elements on the page.
     */
    public int getRecords() {
        return records;
    }

    /**
     *
     * @return total count of elements.
     */
    public int getRecordsTotal() {
        return recordsTotal;
    }

    /**
     * Returns current page index.
     * @return
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     *
     * @return total count of pages
     */
    public int getPages() {
        return pages == 0? 1 : pages;
    }

    /**
     *
     * @return index of the first element on the page.
     */
    public int getFirst() {
        return (pageIndex * records) - records;
    }

    /**
     *
     * @return elements of the page.
     */
    public List<?> getModel() {
        return model;
    }

    /**
     * Sets page.
     * @param pageIndex number of wished page. If more then pages, sets DEFAULT.
     */
    public void setPageIndex(int pageIndex) {
        if(pageIndex > pages)
            pageIndex = DEFAULT_PAGE_INDEX;
        this.pageIndex = pageIndex;
        updateModel();
    }

    /**
     * Sets count of elements on each page.
     * @param count count of elements on each page.
     */
    public void setRecordCountOnPage(int count){
        if(count > 0){
            records = count;
            updateCountOfPages();
            updateModel();
            setPageIndex(DEFAULT_PAGE_INDEX);
        }
    }
}
