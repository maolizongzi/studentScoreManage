package org.graduate.teacher.service;


import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.teacher.repository.model.TeacherQueryParam;
import org.graduate.teacher.service.entity.TeacherEntity;
import org.graduate.teacher.service.entity.TeacherLoginEntity;

import java.util.List;

public interface TeacherService {

    TeacherEntity addTeacher(TeacherEntity teacherEntity);

    TeacherEntity updateTeacher(TeacherEntity teacherEntity);

    TeacherLoginEntity loginByTeacherNo(String teacherNo, String password);

    QueryResultEntity<List<TeacherEntity>> query(TeacherQueryParam teacherQueryParam);
}
