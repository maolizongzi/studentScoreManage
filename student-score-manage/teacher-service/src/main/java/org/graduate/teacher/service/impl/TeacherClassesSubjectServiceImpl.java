package org.graduate.teacher.service.impl;

import org.graduate.teacher.repository.dao.TeacherClassesSubjectDao;
import org.graduate.teacher.repository.model.TeacherClassesSubject;
import org.graduate.teacher.service.TeacherClassesSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeacherClassesSubjectServiceImpl implements TeacherClassesSubjectService {
    TeacherClassesSubjectDao teacherClassesSubjectDao;

    @Autowired
    public TeacherClassesSubjectServiceImpl(TeacherClassesSubjectDao teacherClassesSubjectDao) {
        this.teacherClassesSubjectDao = teacherClassesSubjectDao;
    }

    @Override
    public void addTeacherClassesSubject(TeacherClassesSubject teacherClassesSubject) {
        teacherClassesSubject.setNo(UUID.randomUUID().toString());
        teacherClassesSubjectDao.save(teacherClassesSubject);
    }
}
