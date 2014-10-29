package ua.nure.sigma.store.entity;

import java.util.Date;

/**
 * Created by Maksim Sinkevich on 28.10.2014.
 */
public class CustomerDetails {
    private String name;
    private Date startDate;
    private Date endDate;
    private int daysLeft;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }
}
