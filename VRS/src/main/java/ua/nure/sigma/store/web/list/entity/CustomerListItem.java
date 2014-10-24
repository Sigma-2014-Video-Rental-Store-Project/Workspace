package ua.nure.sigma.store.web.list.entity;

import ua.nure.sigma.store.entity.Customer;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergey Laposhko on 21.10.14.
 */
public class CustomerListItem extends Customer {

    private int copiesRented;
    private Date returnDate;
    private long leftDays;

    public long getLeftDays() {
        return (returnDate == null)? 0 : TimeUnit.DAYS.convert(returnDate.getTime()-new Date().getTime(),TimeUnit.MILLISECONDS);
    }

    /**
     * Initiates custmerListItem with customer and 2 more params.
     * @param baseCustomer will be used to init base fields.
     * @param returnDate the nearest date to today, that is more then today. if today is 1.10
     * @param copiesRented
     */
    public CustomerListItem(Customer baseCustomer, Date returnDate, int copiesRented){
        super(baseCustomer);
        this.copiesRented = copiesRented;
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     *
     * @return TODO maybe you need redo filling this field.
     */
    public int getCopiesRented() {
        return copiesRented;
    }

    public void setCopiesRented(int copiesRented) {
        this.copiesRented = copiesRented;
    }

}
