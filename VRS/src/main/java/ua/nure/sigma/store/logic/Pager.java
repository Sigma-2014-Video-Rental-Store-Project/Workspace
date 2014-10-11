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

    public void updateModel() {
        int fromIndex = getFirst();
        int toIndex = getFirst() + records;

        if(toIndex > this.recordsTotal) {
            toIndex = this.recordsTotal;
        }

        this.model = origModel.subList(fromIndex, toIndex);
    }

    public void next() {
        if(this.pageIndex < pages) {
            this.pageIndex++;
        }

        updateModel();
    }

    public void prev() {
        if(this.pageIndex > 1) {
            this.pageIndex--;
        }

        updateModel();
    }

    public int getRecords() {
        return records;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPages() {
        return pages == 0? 1 : pages;
    }

    public int getFirst() {
        return (pageIndex * records) - records;
    }

    public List<?> getModel() {
        return model;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        updateModel();
    }

    public void setRecordCountOnPage(int count){
        if(count > 0){
            records = count;
            updateCountOfPages();
            updateModel();
            setPageIndex(DEFAULT_PAGE_INDEX);
        }
    }
}
