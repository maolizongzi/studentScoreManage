package org.graduate.student.service.impl;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.student.repository.dao.ExamScoreDao;
import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.repository.model.ExamScoreQueryParam;
import org.graduate.student.service.ExamScoreService;
import org.graduate.student.service.entity.ExamScoreEntity;
import org.graduate.student.service.utility.ExamScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExamScoreServiceImpl implements ExamScoreService {

    private final ExamScoreDao examScoreDao;

    @Autowired
    public ExamScoreServiceImpl(ExamScoreDao examScoreDao) {
        this.examScoreDao = examScoreDao;
    }

    @Override
    public ExamScoreEntity addExamScore(ExamScoreEntity examScoreEntity) {
            ExamScore examScore= ExamScoreUtil.toExamScore(examScoreEntity);
            examScoreDao.save(examScore);
            return ExamScoreUtil.toExamScoreEntity(examScore);
    }

    @Override
    public QueryResultEntity<List<ExamScoreEntity>> query(ExamScoreQueryParam examScoreQueryParam) {
        List<ExamScore> examScores =examScoreDao.query(examScoreQueryParam);
        Integer count = examScoreDao.queryCount(examScoreQueryParam);
        List<ExamScoreEntity> examScoreEntitieList = new ArrayList<>();
        examScores.forEach(o -> examScoreEntitieList.add(ExamScoreUtil.toExamScoreEntity(o)));
        QueryResultEntity<List<ExamScoreEntity>> queryResultEntity = new QueryResultEntity<>(examScoreQueryParam.getPageSize(), count);
        queryResultEntity.setData(examScoreEntitieList);
        queryResultEntity.setCode("00");
        queryResultEntity.setResult("success");
        return queryResultEntity;
    }

    @Override
    public ExamScoreQueryParam countByPhaseScore(ExamScoreQueryParam examScoreQueryParam) {
        return examScoreDao.countByPhaseScore(examScoreQueryParam);
    }

    @Override
    public List<ExamScoreQueryParam> countByGradeScore(ExamScoreQueryParam examScoreQueryParam) {
        return examScoreDao.countByGradeScore(examScoreQueryParam);
    }

    @Override
    public List<ExamScoreQueryParam> queryStudentAllScore(ExamScoreQueryParam examScoreQueryParam) {
        return examScoreDao.queryStudentAllScore(examScoreQueryParam);
    }

}
