package ua.nure.sigma.store.dao;

import ua.nure.sigma.store.entity.Sex;

import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public interface SexDAO {
    Sex findSexByID(int id, int locale);
    List<Sex> findAllSex(int locale);
    Sex findSexIDBySexName(String sex);
}
