package org.graduate.student.service;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.repository.model.ExamScoreQueryParam;
import org.graduate.student.service.entity.ExamScoreEntity;

import java.util.List;

public interface ExamScoreService {

    void addExamScore(ExamScore examScore);

    QueryResultEntity<List<ExamScoreEntity>> query(ExamScoreQueryParam examScoreQueryParam);
}
