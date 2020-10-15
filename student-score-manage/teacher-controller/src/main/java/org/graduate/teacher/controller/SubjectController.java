package org.graduate.teacher.controller;

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
    public SubjectEntity addSubject(@RequestBody SubjectEntity subjectEntity) {
        return subjectService.addSubject(subjectEntity);
    }

    public SubjectEntity updateSubject(@RequestParam SubjectEntity subjectEntity) {
        return subjectService.updateSubject(subjectEntity);
    }

    @GetMapping("query")
    public QueryResultEntity<List<SubjectEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "no", required = false) String no,
            @RequestParam(value = "name", required = false) String name
    ) {
        SubjectQueryParam subjectQueryParam = new SubjectQueryParam();
        subjectQueryParam.setPageSize(pageSize);
        subjectQueryParam.setCurrentPage(currentPage);
        subjectQueryParam.setId(id);
        subjectQueryParam.setNo(no);
        subjectQueryParam.setName(name);
        return subjectService.query(subjectQueryParam);
    }
}