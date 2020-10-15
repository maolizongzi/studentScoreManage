package org.graduate.student.controller;

import org.apache.commons.lang3.StringUtils;
import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.student.repository.model.StudentQueryParam;
import org.graduate.student.service.StudentService;
import org.graduate.student.service.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("register")
    public StudentEntity addStudent(@RequestBody StudentEntity studentEntity) {
        return studentService.addStudent(studentEntity);
    }

    @PostMapping("update")
    public StudentEntity updateStudent(@RequestBody StudentEntity studentEntity) {
        return studentService.updateStudent(studentEntity);
    }


    @GetMapping("query")
    public QueryResultEntity<List<StudentEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam(value = "no", required = false) String no
    ) {
        StudentQueryParam studentQueryParam = new StudentQueryParam();
        studentQueryParam.setPageSize(pageSize);
        studentQueryParam.setCurrentPage(currentPage);
        if (StringUtils.isNoneEmpty(no)) {
            studentQueryParam.setNo(no);
        }
        return studentService.query(studentQueryParam);
    }
}
