package org.graduate.teacher.controller;

import org.graduate.teacher.controller.utility.TeacherClassesSubjectUtil;
import org.graduate.teacher.repository.model.TeacherClassesSubject;
import org.graduate.teacher.service.TeacherClassesSubjectService;
import org.graduate.teacher.service.entity.TeacherClassesSubjectEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher/classes/subject")
public class TeacherClassesSubjectController {

    private TeacherClassesSubjectService teacherClassesSubjectService;

    public TeacherClassesSubjectController(TeacherClassesSubjectService teacherClassesSubjectService) {
        this.teacherClassesSubjectService = teacherClassesSubjectService;
    }

    @PostMapping("add")
    public TeacherClassesSubjectEntity addClassesSubject(TeacherClassesSubjectEntity teacherClassesSubjectEntity) {
        TeacherClassesSubject teacherClassesSubject = TeacherClassesSubjectUtil.toTeacherClassesSubject(teacherClassesSubjectEntity);
        teacherClassesSubjectService.addTeacherClassesSubject(teacherClassesSubject);
        return TeacherClassesSubjectUtil.toTeacherClassesSubjectEntity(teacherClassesSubject);

    }
}
