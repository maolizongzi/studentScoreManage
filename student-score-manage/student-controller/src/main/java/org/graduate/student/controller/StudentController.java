package org.graduate.student.controller;

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

    @PostMapping("add")
    public StudentEntity addStudent(@RequestBody StudentEntity studentEntity) {
        return studentService.addStudent(studentEntity);
    }


    @GetMapping("query")
    public QueryResultEntity<List<StudentEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam(value = "studentNo", required = false) String studentNo
    ) {
        StudentQueryParam studentQueryParam = new StudentQueryParam();
        studentQueryParam.setPageSize(pageSize);
        studentQueryParam.setCurrentPage(currentPage);
        return studentService.query(studentQueryParam);
    }
}
