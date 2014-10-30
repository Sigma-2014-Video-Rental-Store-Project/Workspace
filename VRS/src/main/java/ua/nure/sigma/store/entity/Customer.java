package ua.nure.sigma.store.entity;

import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;

/**
 * Created by vlad on 13.10.14.
 */

///class Customer doesn't have setBonus method. You can increase or dicrease bonus
public class Customer {
    private int customerID;
    private String lastName;
    private String firstName;
    private String middleName;
    private String customerEmail;
    private String customerPhone;
    private int sexID;
    private String customerPhoto;
    private long bonus;

    public Customer(){}

    public Customer(Customer customer){
        customerID = customer.getCustomerID();
        lastName = customer.getLastName();
        firstName = customer.getFirstName();
        middleName = customer.getMiddleName();
        customerEmail = customer.getCustomerEmail();
        customerPhone = customer.getCustomerPhone();
        sexID = customer.getSexID();
        customerPhoto = customer.getCustomerPhoto();
        bonus = customer.getBonus();
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getSexID() {
        return sexID;
    }

    public void setSexID(int sexID) {
        this.sexID = sexID;
    }

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public long getBonus() {
        return bonus;
    }

    //if you want increase count of bonus please input positive number, otherwise input negative number
    public void addBonus(long bonus) throws NotEnoughOfBonusExeption {
        if (this.bonus + bonus < 0){
            throw new NotEnoughOfBonusExeption();
        }
        this.bonus += bonus;
    }
}
