package org.graduate.subject.service.impl;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.subject.repository.dao.ExamDao;
import org.graduate.subject.repository.model.Exam;
import org.graduate.subject.repository.model.ExamQueryParam;
import org.graduate.subject.service.ExamService;
import org.graduate.subject.service.entity.ExamEntity;
import org.graduate.subject.service.utility.ExamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamDao examDao;

    @Autowired
    public ExamServiceImpl(ExamDao examDao) {
        this.examDao = examDao;
    }

    @Override
    public void addExam(Exam exam) {
        exam.setNo(UUID.randomUUID().toString());
        examDao.save(exam);
    }

    @Override
    public QueryResultEntity<List<ExamEntity>> query(ExamQueryParam examQueryParam) {
        List<Exam> examList= examDao.query(examQueryParam);
        Integer count = examDao.queryCount(examQueryParam);
        List<ExamEntity> examEntities = new ArrayList<>();
        examList.forEach(o -> examEntities.add(ExamUtil.toExamEntity(o)));
        QueryResultEntity<List<ExamEntity>> queryResultEntity = new QueryResultEntity<>(examQueryParam.getPageSize(), count);
        queryResultEntity.setData(examEntities);
        queryResultEntity.setCode("00");
        queryResultEntity.setResult("success");
        return queryResultEntity;
    }
}
