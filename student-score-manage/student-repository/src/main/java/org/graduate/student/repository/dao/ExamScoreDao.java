package org.graduate.student.repository.dao;

import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.repository.model.ExamScoreQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExamScoreDao {
    void save(ExamScore examScore);

    List<ExamScore> query(ExamScoreQueryParam examScoreQueryParam);

    Integer queryCount(ExamScoreQueryParam examScoreQueryParam);

    /**
     * 分阶段统计人数
     * <60  不及格
     * 60<x<75 及格
     * 75<x<85 中等
     * 85<x<=100 优秀
     * @param examScoreQueryParam
     * @return
     */
    ExamScoreQueryParam countByPhaseScore(ExamScoreQueryParam examScoreQueryParam);

    //按照每届统计记录
    List<ExamScoreQueryParam> countByGradeScore(ExamScoreQueryParam examScoreQueryParam);

    /**
     * 查询每个班级学生每科成绩以及按照汇总成绩排名
     */
    List<ExamScoreQueryParam> queryStudentAllScore(ExamScoreQueryParam examScoreQueryParam);
}
