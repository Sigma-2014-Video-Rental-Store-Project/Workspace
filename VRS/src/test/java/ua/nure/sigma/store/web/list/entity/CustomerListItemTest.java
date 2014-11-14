package ua.nure.sigma.store.web.list.entity;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.sigma.store.entity.Customer;

public class CustomerListItemTest {

    @Test
    public final void testGetLeftDays() {
	CustomerListItem item = new CustomerListItem(new Customer(), new Date(), 1);
	Assert.assertEquals(0, item.getLeftDays());
	item.setReturnDate(null);
	Assert.assertTrue("returns small number if there is no rents", item.getLeftDays() > 99999999);
    }

    @Test
    public final void testGetReturnDate() {
	Date date = new Date(1233123132);
	CustomerListItem item = new CustomerListItem(new Customer(), null, 1);
	item.setReturnDate(date);
	Assert.assertEquals("Return date wrong getter or setter.", date, item.getReturnDate());
    }

    @Test
    public final void testSetCopiesRented() {
	CustomerListItem item = new CustomerListItem(new Customer(), null, 0);
	item.setCopiesRented(12);
	Assert.assertEquals("Wrong copies left.", 12, item.getCopiesRented());
    }

}
