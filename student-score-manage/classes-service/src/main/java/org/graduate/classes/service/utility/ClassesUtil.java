package org.graduate.classes.service.utility;

import org.graduate.classes.repository.model.Classes;
import org.graduate.classes.service.entity.ClassesEntity;
import org.springframework.beans.BeanUtils;

public class ClassesUtil {

    public static ClassesEntity toClassesEntity(Classes classes) {
        ClassesEntity classesEntity = new ClassesEntity();
        BeanUtils.copyProperties(classes, classesEntity);
        return classesEntity;

    }

    public static Classes toClasses(ClassesEntity classesEntity) {
        Classes classes = new Classes();
        BeanUtils.copyProperties(classesEntity, classes);
        return classes;
    }
}
