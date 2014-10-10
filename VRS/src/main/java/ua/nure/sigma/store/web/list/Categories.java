package ua.nure.sigma.store.web.list;

import ua.nure.sigma.store.entity.Category;

import java.util.List;

/**
 * Created by Максим on 10.10.2014.
 */
public class Categories {
    private List<Category> categories;

    public Categories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getModel() {
        return categories;
    }
}
