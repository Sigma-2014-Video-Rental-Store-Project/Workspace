package ua.nure.sigma.store.comparators;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

public class CustomerReturnDateComparatorTest {

	@Test
	public void testCompare() throws NotEnoughOfBonusExeption {
		CustomerReturnDateComparator c = new CustomerReturnDateComparator();
		CustomerListItem cd1 = new CustomerListItem(new Customer(), new Date(), 0);
		CustomerListItem cd2 = new CustomerListItem(new Customer(), new Date(), 0);
		
		cd1.setReturnDate(null);
		cd2.setReturnDate(null);
		assertTrue("CustomerDetailsStatusComparator !equals! error", c.compare(cd1, cd2) == 0);
		cd1.setReturnDate(new Date());
		cd2.setReturnDate(null);
		assertTrue("CustomerDetailsStatusComparator !more! error", c.compare(cd1, cd2) > 0);
		cd1.setReturnDate(null);
		cd2.setReturnDate(new Date());
		assertTrue("CustomerDetailsStatusComparator !less! error", c.compare(cd1, cd2) < 0);
		cd1.setReturnDate(new Date());
		cd2.setReturnDate(new Date());
		assertTrue("CustomerDetailsStatusComparator !equals! error", c.compare(cd1, cd2) == 0);
	}

}
