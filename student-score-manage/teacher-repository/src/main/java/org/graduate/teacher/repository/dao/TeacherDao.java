package org.graduate.teacher.repository.dao;

import org.graduate.teacher.repository.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao {
    void save(Teacher teacher);
}
