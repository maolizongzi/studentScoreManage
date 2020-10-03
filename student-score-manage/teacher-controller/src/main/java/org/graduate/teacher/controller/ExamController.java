package org.graduate.teacher.controller;

import org.graduate.subject.repository.model.Exam;
import org.graduate.subject.service.ExamService;
import org.graduate.subject.service.entity.ExamEntity;
import org.graduate.teacher.controller.utility.ExamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exam")
public class ExamController {
    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("add")
    public ExamEntity addExam(@RequestBody ExamEntity examEntity) {
        Exam exam = ExamUtil.toExam(examEntity);
        examService.addExam(exam);
        return ExamUtil.toExamEntity(exam);
    }
}
