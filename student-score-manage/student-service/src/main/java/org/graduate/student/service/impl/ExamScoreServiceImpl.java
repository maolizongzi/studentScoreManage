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

@Service
public class ExamScoreServiceImpl implements ExamScoreService {

    private final ExamScoreDao examScoreDao;

    @Autowired
    public ExamScoreServiceImpl(ExamScoreDao examScoreDao) {
        this.examScoreDao = examScoreDao;
    }

    @Override
    public void addExamScore(ExamScore examScore) {
        examScoreDao.save(examScore);
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

}
