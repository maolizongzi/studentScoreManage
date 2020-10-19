package org.graduate.teacher.controller;

import org.graduate.base.general.entity.BaseResultEntity;
import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.classes.repository.model.Classes;
import org.graduate.classes.repository.model.QueryClassesParam;
import org.graduate.classes.service.ClassesService;
import org.graduate.classes.service.entity.ClassesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("classes")
public class ClassesController {

    private final ClassesService classesService;

    @Autowired
    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @PostMapping("add")
    public BaseResultEntity<ClassesEntity> addClasses(@RequestBody ClassesEntity classesEntity) {
        BaseResultEntity<ClassesEntity> resultEntity = new BaseResultEntity<>();
        resultEntity.setCode("01");
        resultEntity.setResult("fail");
        try {
            ClassesEntity resultClassesEntity = classesService.addClasses(classesEntity);
            resultEntity.setCode("00");
            resultEntity.setResult("success");
            resultEntity.setData(resultClassesEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultEntity;
    }

    @PostMapping("update")
    public BaseResultEntity<ClassesEntity> updateClasses(@RequestBody ClassesEntity classesEntity) {
        BaseResultEntity<ClassesEntity> resultEntity = new BaseResultEntity<>();
        resultEntity.setCode("01");
        resultEntity.setResult("fail");
        try {
            ClassesEntity resultClassesEntity = classesService.updateClasses(classesEntity);
            resultEntity.setCode("00");
            resultEntity.setResult("success");
            resultEntity.setData(resultClassesEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultEntity;
    }


    @GetMapping("query")
    public QueryResultEntity<List<ClassesEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "no", required = false) String no,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "grade", required = false) Integer grade
    ) {
        QueryClassesParam queryClassesParam = new QueryClassesParam(currentPage, pageSize);
        queryClassesParam.setId(id);
        queryClassesParam.setNo(no);
        queryClassesParam.setName(name);
        queryClassesParam.setGrade(grade);
        return classesService.query(queryClassesParam);
    }
}
