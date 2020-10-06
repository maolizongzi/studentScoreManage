package org.graduate.teacher.service;


import org.graduate.teacher.service.entity.TeacherEntity;
import org.graduate.teacher.service.entity.TeacherLoginEntity;

public interface TeacherService {

    void addTeacher(TeacherEntity teacherEntity);

    TeacherLoginEntity loginByTeacherNo(String teacherNo, String password);


}
