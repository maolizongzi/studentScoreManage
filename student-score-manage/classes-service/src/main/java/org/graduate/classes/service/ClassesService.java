package org.graduate.classes.service;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.classes.repository.model.Classes;
import org.graduate.classes.repository.model.QueryClassesParam;
import org.graduate.classes.service.entity.ClassesEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassesService {
    ClassesEntity addClasses(ClassesEntity classesEntity);

    QueryResultEntity<List<ClassesEntity>> query(QueryClassesParam queryClassesParam);
}
