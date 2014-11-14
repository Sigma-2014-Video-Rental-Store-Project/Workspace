package ua.nure.sigma.store.comparators;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;
import ua.nure.sigma.store.web.list.entity.CustomerListItem;

public class CustomerBonusComparatorTest {

	@Test
	public void testCompare() throws NotEnoughOfBonusExeption {
		CustomerBonusComparator c = new CustomerBonusComparator();
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		customer1.addBonus(1);
		CustomerListItem item1 = new CustomerListItem(customer1, null, 0);
		CustomerListItem item2 = new CustomerListItem(customer2, null, 0);
		
		assertTrue("CustomerBonusComparator !more! error", c.compare(item1, item2) > 0);
		item1.addBonus(-1);
		assertTrue("CustomerBonusComparator !equls! error", c.compare(item1, item2) == 0);
		item2.addBonus(1);
		assertTrue("CustomerBonusComparator !less! error", c.compare(item1, item2) < 0);
	}

}
