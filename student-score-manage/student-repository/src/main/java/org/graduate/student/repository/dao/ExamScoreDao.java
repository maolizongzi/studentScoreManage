package org.graduate.student.repository.dao;

import org.graduate.student.repository.model.ExamScore;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamScoreDao {
    void save(ExamScore examScore);
}
