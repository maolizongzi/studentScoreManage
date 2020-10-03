package org.graduate.subject.service.impl;

import org.graduate.subject.repository.dao.ExamDao;
import org.graduate.subject.repository.model.Exam;
import org.graduate.subject.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
