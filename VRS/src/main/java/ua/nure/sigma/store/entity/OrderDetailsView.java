package ua.nure.sigma.store.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 04.11.14.
 */
public class OrderDetailsView {
    private long rentId;
    private String customerName;
    private Date rentedDate;
    private List<RentedFilmView> filmViewList;
    private long usedBonus;
    private long total;
    private long forPaid;

    public List<RentedFilmView> getModel(){
        return filmViewList;
    }

    public OrderDetailsView(long rentId, String customerName, Date rentedDate, List<RentedFilmView> filmViewList, long usedBonus, long total, long forPaid) {
        this.rentId = rentId;
        this.customerName = customerName;
        this.rentedDate = rentedDate;
        this.filmViewList = filmViewList;
        this.usedBonus = usedBonus;
        this.total = total;
        this.forPaid = forPaid;
    }

    public long getUsedBonus() {
        return usedBonus;
    }

    public long getTotal() {
        return total;
    }

    public long getForPaid() {
        return forPaid;
    }

    public long getRentId() {

        return rentId;
    }

    public void setRentId(long rentId) {
        this.rentId = rentId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setRentedDate(Date rentedDate) {
        this.rentedDate = rentedDate;
    }

    public void setFilmViewList(List<RentedFilmView> filmViewList) {
        this.filmViewList = filmViewList;
    }

    public void setUsedBonus(long usedBonus) {
        this.usedBonus = usedBonus;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setForPaid(long forPaid) {
        this.forPaid = forPaid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getRentedDate() {
        return rentedDate;
    }

    public List<RentedFilmView> getFilmViewList() {
        return filmViewList;
    }
}
