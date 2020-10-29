package org.graduate.subject.repository.dao;


import org.graduate.subject.repository.model.Exam;
import org.graduate.subject.repository.model.ExamQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamDao {
    void save(Exam exam);

    void update(Exam exam);

    List<Exam> query(ExamQueryParam examQueryParam);

    Integer queryCount(ExamQueryParam examQueryParam);
}
