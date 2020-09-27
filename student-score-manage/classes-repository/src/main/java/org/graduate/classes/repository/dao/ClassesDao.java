package org.graduate.classes.repository.dao;

import org.graduate.classes.repository.model.Classes;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesDao {
    void save(Classes classes);
}