package org.graduate.subject.repository.dao;


import org.graduate.subject.repository.model.Exam;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamDao {
    void save(Exam exam);
}
