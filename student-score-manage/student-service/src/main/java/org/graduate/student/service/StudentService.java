package org.graduate.student.service;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.student.repository.model.StudentQueryParam;
import org.graduate.student.service.entity.StudentEntity;

import java.util.List;

public interface StudentService {

    StudentEntity addStudent(StudentEntity student);

    StudentEntity updateStudent(StudentEntity studentEntity);

    QueryResultEntity<List<StudentEntity>> query(StudentQueryParam studentQueryParam);
}
