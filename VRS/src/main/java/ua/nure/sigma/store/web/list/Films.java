package ua.nure.sigma.store.web.list;

import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.logic.Pager;

import java.util.List;

/**
 * Created by Сергей on 10.10.14.
 */
public class Films {
    private Pager pager;

    public Films(List<Film> films){
        pager = new Pager(films);
    }

    public List<Film> getModel(){
        return (List<Film>) pager.getModel();
    }

    public Pager getPager(){
        return pager;
    }
}
