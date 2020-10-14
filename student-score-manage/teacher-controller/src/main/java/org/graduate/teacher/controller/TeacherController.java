package org.graduate.teacher.controller;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.teacher.repository.model.TeacherQueryParam;
import org.graduate.teacher.service.TeacherService;
import org.graduate.teacher.service.entity.TeacherEntity;
import org.graduate.teacher.service.entity.TeacherLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public TeacherLoginEntity login(@RequestBody TeacherEntity teacherEntity) {
        return teacherService.loginByTeacherNo(teacherEntity.getNo(), teacherEntity.getPassword());
    }

    @PostMapping("register")
    public TeacherEntity register(@RequestBody TeacherEntity teacherEntity) {
        return teacherService.addTeacher(teacherEntity);
    }

    @PostMapping("update")
    public TeacherEntity update(@RequestBody TeacherEntity teacherEntity) {
        return teacherService.updateTeacher(teacherEntity);
    }

    @GetMapping("query")
    public QueryResultEntity<List<TeacherEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage
    ) {
        TeacherQueryParam teacherQueryParam = new TeacherQueryParam();
        teacherQueryParam.setPageSize(pageSize);
        teacherQueryParam.setCurrentPage(currentPage);
        return teacherService.query(teacherQueryParam);
    }
}
