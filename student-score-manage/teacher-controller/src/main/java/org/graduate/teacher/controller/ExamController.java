package org.graduate.teacher.controller;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.subject.repository.model.Exam;
import org.graduate.subject.repository.model.ExamQueryParam;
import org.graduate.subject.service.ExamService;
import org.graduate.subject.service.entity.ExamEntity;
import org.graduate.teacher.controller.utility.ExamUtil;
import org.graduate.teacher.repository.model.TeacherQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("query")
    public QueryResultEntity<List<ExamEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "no", required = false) String no,
            @RequestParam(value = "name", required = false) String name
    ){
        ExamQueryParam examQueryParam = new ExamQueryParam(currentPage, pageSize);
        examQueryParam.setId(id);
        examQueryParam.setNo(no);
        examQueryParam.setName(name);
        return examService.query(examQueryParam);
    }
}
