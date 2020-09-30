package org.graduate.teacher.controller;

import org.graduate.teacher.controller.utility.TeacherUtil;
import org.graduate.teacher.repository.model.Teacher;
import org.graduate.teacher.service.TeacherService;
import org.graduate.teacher.service.entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("login")
    public String login() {
        return "";
    }

    @PostMapping("register")
    public TeacherEntity register(@RequestBody TeacherEntity teacherEntity) {
        Teacher teacher = TeacherUtil.toTeacher(teacherEntity);
        teacherService.addTeacher(teacher);
        return TeacherUtil.toTeacherEntity(teacher);
    }
}
