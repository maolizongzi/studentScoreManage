package org.graduate.student.repository.dao;

import org.graduate.student.repository.model.Student;
import org.graduate.student.repository.model.StudentQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {
    void save(Student student);

    void update(Student student);

    List<Student> query(StudentQueryParam studentQueryParam);

    Integer queryCount(StudentQueryParam studentQueryParam);
}
