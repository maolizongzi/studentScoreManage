package org.graduate.teacher.repository.dao;

import org.graduate.teacher.repository.model.TeacherClassesSubject;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherClassesSubjectDao {
    void save(TeacherClassesSubject teacherClassesSubject);
}
