package ua.nure.sigma.store.comparators;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

public class CustomerNameComparatorTest {

	@Test
	public void testCompare() throws NotEnoughOfBonusExeption {
		CustomerNameComparator comp = new CustomerNameComparator();
		Customer c1 = new Customer();
		Customer c2 = new Customer();
		
		CustomerListItem cd1 = new CustomerListItem(c1, new Date(), 1);
		CustomerListItem cd2 = new CustomerListItem(c2, new Date(), 1);
		
		cd1.setLastName(null);
		cd2.setLastName(null);
		assertTrue("CustomerDetailsStatusComparator !equals! error", comp.compare(cd1, cd2) == 0);
		cd1.setLastName("A");
		cd2.setLastName(null);
		assertTrue("CustomerDetailsStatusComparator !more! error", comp.compare(cd1, cd2) > 0);
		cd1.setLastName(null);
		cd2.setLastName("B");
		assertTrue("CustomerDetailsStatusComparator !less! error", comp.compare(cd1, cd2) < 0);
		cd1.setLastName("a");
		cd2.setLastName("a");
		assertTrue("CustomerDetailsStatusComparator !equals! error", comp.compare(cd1, cd2) == 0);
	}
	
}
