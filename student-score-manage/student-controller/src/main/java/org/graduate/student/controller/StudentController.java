package org.graduate.student.controller;

import org.apache.commons.lang3.StringUtils;
import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.student.repository.model.StudentQueryParam;
import org.graduate.student.service.StudentService;
import org.graduate.student.service.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "no", required = false) String no,
            @RequestParam(value = "gender", required = false) Integer gender,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "identityNo", required = false) String identityNo,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "birthday", required = false) Date birthday,
            @RequestParam(value = "admissionDate", required = false) Date admissionDate,
            @RequestParam(value = "graduationDate", required = false) Date graduationDate,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "classesId", required = false) Long classesId
    ) {
        StudentQueryParam studentQueryParam = new StudentQueryParam();
        studentQueryParam.setPageSize(pageSize);
        studentQueryParam.setCurrentPage(currentPage);
        studentQueryParam.setId(id);
        studentQueryParam.setNo(no);
        studentQueryParam.setName(name);
        studentQueryParam.setGender(gender);
        studentQueryParam.setPassword(password);
        studentQueryParam.setIdentityNo(identityNo);
        studentQueryParam.setAddress(address);
        studentQueryParam.setBirthday(birthday);
        studentQueryParam.setAdmissionDate(admissionDate);
        studentQueryParam.setGraduationDate(graduationDate);
        studentQueryParam.setStatus(status);
        studentQueryParam.setClassesId(classesId);
        return studentService.query(studentQueryParam);
    }
}
