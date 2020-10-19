package org.graduate.student.repository.dao;

import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.repository.model.ExamScoreQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamScoreDao {
    void save(ExamScore examScore);

    List<ExamScore> query(ExamScoreQueryParam examScoreQueryParam);

    Integer queryCount(ExamScoreQueryParam examScoreQueryParam);

}
