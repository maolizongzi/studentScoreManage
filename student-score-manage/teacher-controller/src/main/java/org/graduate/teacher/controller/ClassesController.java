package org.graduate.teacher.controller;

import org.graduate.classes.repository.model.Classes;
import org.graduate.classes.service.ClassesService;
import org.graduate.classes.service.entity.ClassesEntity;
import org.graduate.teacher.controller.utility.ClassesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("classes")
public class ClassesController {

    private final ClassesService classesService;

    @Autowired
    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @PostMapping("add")
    public ClassesEntity addClasses(@RequestBody ClassesEntity classesEntity) {
        Classes classes = ClassesUtil.toClasses(classesEntity);
        classesService.addClasses(classes);
        return ClassesUtil.toClassesEntity(classes);
    }
}
