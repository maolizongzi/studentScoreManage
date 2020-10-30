package org.graduate.teacher.controller;

import org.graduate.base.general.entity.BaseResultEntity;
import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.subject.repository.model.SubjectQueryParam;
import org.graduate.subject.service.SubjectService;
import org.graduate.subject.service.entity.SubjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("add")
    public BaseResultEntity<SubjectEntity> addSubject(@RequestBody SubjectEntity subjectEntity) {
        BaseResultEntity<SubjectEntity> baseResultEntity = new BaseResultEntity<>();
        baseResultEntity.setCode("01");
        baseResultEntity.setResult("fail");
        try {
            SubjectEntity resultSubjectEntity = subjectService.addSubject(subjectEntity);
            baseResultEntity.setCode("00");
            baseResultEntity.setResult("success");
            baseResultEntity.setData(resultSubjectEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseResultEntity;
    }
    @GetMapping("delete")
    public BaseResultEntity<SubjectEntity> deleteSubject(
            @RequestParam(value = "id", required = false) Long id){
        BaseResultEntity<SubjectEntity> baseResultEntity = new BaseResultEntity<>();
        baseResultEntity.setCode("01");
        baseResultEntity.setResult("fail");
        try {
            subjectService.deleteSubject(id);
            baseResultEntity.setCode("00");
            baseResultEntity.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return baseResultEntity;
    }
    @PostMapping("update")
    public BaseResultEntity<SubjectEntity> updateSubject(@RequestBody SubjectEntity subjectEntity) {
        BaseResultEntity<SubjectEntity> baseResultEntity = new BaseResultEntity<>();
        baseResultEntity.setCode("01");
        baseResultEntity.setResult("fail");
        try {
            SubjectEntity resultSubjectEntity = subjectService.updateSubject(subjectEntity);
            baseResultEntity.setCode("00");
            baseResultEntity.setResult("success");
            baseResultEntity.setData(resultSubjectEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseResultEntity;
    }

    @GetMapping("query")
    public QueryResultEntity<List<SubjectEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "no", required = false) String no,
            @RequestParam(value = "name", required = false) String name
    ) {
        SubjectQueryParam subjectQueryParam = new SubjectQueryParam(currentPage, pageSize);
        subjectQueryParam.setId(id);
        subjectQueryParam.setNo(no);
        subjectQueryParam.setName(name);
        return subjectService.query(subjectQueryParam);
    }
}