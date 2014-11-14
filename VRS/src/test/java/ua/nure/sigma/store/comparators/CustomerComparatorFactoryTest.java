package ua.nure.sigma.store.comparators;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.sigma.store.web.list.entity.CustomerListItem;

public class CustomerComparatorFactoryTest {

	@Test
	public final void testGetComparator() {
		new CustomerComparatorFactory();
	}
	
	@Test
	public void testGetComparatorNotNull(){
		Comparator<CustomerListItem> c = CustomerComparatorFactory.getComparator(CustomerComparatorFactory.CUSTOMER_BONUS_COMPARATOR);
		Assert.assertNotNull(c);
	}
	
}
