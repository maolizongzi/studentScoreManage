package org.graduate.teacher.controller;

import org.graduate.subject.repository.model.Subject;
import org.graduate.subject.service.SubjectService;
import org.graduate.subject.service.entity.SubjectEntity;
import org.graduate.teacher.controller.utility.SubjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Subject subject = SubjectUtil.toSubject(subjectEntity);
        subjectService.addSubject(subject);
        return SubjectUtil.toSubjectEntity(subject);
    }
}