package org.graduate.student.repository.dao;

import org.graduate.student.repository.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao {
    void save(Student student);
}
