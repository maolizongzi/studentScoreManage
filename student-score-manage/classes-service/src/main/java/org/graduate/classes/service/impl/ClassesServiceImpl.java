package org.graduate.classes.service.impl;

import org.graduate.classes.repository.dao.ClassesDao;
import org.graduate.classes.repository.model.Classes;
import org.graduate.classes.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassesServiceImpl implements ClassesService {
    private final ClassesDao classesDao;

    @Autowired
    public ClassesServiceImpl(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }

    @Override
    public void addClasses(Classes classes) {
        classesDao.save(classes);
    }
}
