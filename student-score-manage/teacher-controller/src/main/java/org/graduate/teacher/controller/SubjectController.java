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

    @GetMapping("query")
    public QueryResultEntity<List<SubjectEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage
    ) {
        SubjectQueryParam subjectQueryParam = new SubjectQueryParam();
        subjectQueryParam.setPageSize(pageSize);
        subjectQueryParam.setCurrentPage(currentPage);
        return subjectService.query(subjectQueryParam);
    }
}