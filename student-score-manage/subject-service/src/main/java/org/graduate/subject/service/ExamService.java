package org.graduate.subject.service;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.subject.repository.model.Exam;
import org.graduate.subject.repository.model.ExamQueryParam;
import org.graduate.subject.service.entity.ExamEntity;

import java.util.List;

public interface ExamService {

    void addExam(Exam exam);

    QueryResultEntity<List<ExamEntity>> query(ExamQueryParam examQueryParam);
}
