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
