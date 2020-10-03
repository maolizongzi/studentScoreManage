package org.graduate.subject.service.impl;

import org.graduate.subject.repository.dao.SubjectDao;
import org.graduate.subject.repository.model.Subject;
import org.graduate.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectServiceImpl(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public void addSubject(Subject subject) {
        subjectDao.save(subject);
    }
}
