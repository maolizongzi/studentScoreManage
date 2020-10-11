package org.graduate.classes.repository.dao;

import org.graduate.classes.repository.model.Classes;
import org.graduate.classes.repository.model.QueryClassesParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesDao {
    void save(Classes classes);

    Integer queryCount(QueryClassesParam queryClassesParam);

    List<Classes> query(QueryClassesParam queryClassesParam);
}