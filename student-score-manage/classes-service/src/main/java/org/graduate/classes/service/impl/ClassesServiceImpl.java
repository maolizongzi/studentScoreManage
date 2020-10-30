package org.graduate.classes.service.impl;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.classes.repository.dao.ClassesDao;
import org.graduate.classes.repository.model.Classes;
import org.graduate.classes.repository.model.QueryClassesParam;
import org.graduate.classes.service.ClassesService;
import org.graduate.classes.service.entity.ClassesEntity;
import org.graduate.classes.service.utility.ClassesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClassesServiceImpl implements ClassesService {
    private final ClassesDao classesDao;

    @Autowired
    public ClassesServiceImpl(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }

    @Override
    public ClassesEntity addClasses(ClassesEntity classesEntity) {
        Classes classes = ClassesUtil.toClasses(classesEntity);
        String afterName=classes.getName().substring(0,4);
        QueryClassesParam param=new QueryClassesParam(1,1);
        param.setNameGrade(afterName);
        param.setGrade(classes.getGrade());
        Integer classesCount= classesDao.queryCount(param);
        classes.setNo(buildStudentNo(afterName,classesCount==0?1:classesCount+1));
        classesDao.save(classes);
        return ClassesUtil.toClassesEntity(classes);
    }

    @Override
    public ClassesEntity updateClasses(ClassesEntity classesEntity) {
        Classes classes = ClassesUtil.toClasses(classesEntity);
        classesDao.update(classes);
        return ClassesUtil.toClassesEntity(classes);
    }

    @Override
    public QueryResultEntity<List<ClassesEntity>> query(QueryClassesParam queryClassesParam) {

        List<Classes> classes = classesDao.query(queryClassesParam);
        Integer count = classesDao.queryCount(queryClassesParam);

        List<ClassesEntity> classesEntities = new ArrayList<>();
        classes.forEach(o -> classesEntities.add(ClassesUtil.toClassesEntity(o)));
        QueryResultEntity<List<ClassesEntity>> queryResultEntity = new QueryResultEntity<>(queryClassesParam.getPageSize(), count);
        queryResultEntity.setData(classesEntities);
        queryResultEntity.setCode("00");
        queryResultEntity.setResult("success");
        return queryResultEntity;
    }
    private static String buildStudentNo( String name,Integer id) {
        return name+String.format("%03d", id);
    }

}
