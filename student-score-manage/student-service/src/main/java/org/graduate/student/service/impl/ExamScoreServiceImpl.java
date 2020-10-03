package org.graduate.student.service.impl;

import org.graduate.student.repository.dao.ExamScoreDao;
import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.service.ExamScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
