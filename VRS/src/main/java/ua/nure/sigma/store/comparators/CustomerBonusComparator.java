package ua.nure.sigma.store.comparators;

import ua.nure.sigma.store.web.list.entity.CustomerListItem;

import java.util.Comparator;

/**
 * Created by Сергей on 22.10.14.
 */
public class CustomerBonusComparator implements Comparator<CustomerListItem> {

    @Override
    public int compare(CustomerListItem o1, CustomerListItem o2) {
        long res = o1.getBonus() - o2.getBonus();
        if (res == 0)
            return 0;
        return res > 0 ? 1 : -1;
    }

}
