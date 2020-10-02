package org.graduate.student.controller;

import org.graduate.student.controller.utility.StudentUtil;
import org.graduate.student.repository.model.Student;
import org.graduate.student.service.StudentService;
import org.graduate.student.service.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Student student = StudentUtil.toStudent(studentEntity);
        studentService.addStudent(student);
        return StudentUtil.toStudentEntity(student);
    }
}
