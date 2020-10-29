package org.graduate.student.service;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.repository.model.ExamScoreQueryParam;
import org.graduate.student.service.entity.ExamScoreEntity;

import java.util.List;
import java.util.Map;

public interface ExamScoreService {

    ExamScoreEntity addExamScore(ExamScoreEntity examScoreEntity);

    QueryResultEntity<List<ExamScoreEntity>> query(ExamScoreQueryParam examScoreQueryParam);

    /**
     * 统计每个分数段的人数
     */
    ExamScoreQueryParam countByPhaseScore(ExamScoreQueryParam examScoreQueryParam);

    /**
     * 统计每届每个分数段的人数
     */
    List<ExamScoreQueryParam> countByGradeScore(ExamScoreQueryParam examScoreQueryParam);
    /**
     * 查询每个班级学生每科成绩以及按照汇总成绩排名
     */
    List<ExamScoreQueryParam> queryStudentAllScore(ExamScoreQueryParam examScoreQueryParam);

}
