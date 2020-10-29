package org.graduate.subject.service.impl;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.subject.repository.dao.SubjectDao;
import org.graduate.subject.repository.model.Subject;
import org.graduate.subject.repository.model.SubjectQueryParam;
import org.graduate.subject.service.SubjectService;
import org.graduate.subject.service.entity.SubjectEntity;
import org.graduate.subject.service.utility.SubjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectServiceImpl(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public SubjectEntity addSubject(SubjectEntity subjectEntity) {
        Integer count=subjectDao.queryCount(new SubjectQueryParam());
        Subject subject = SubjectUtil.toSubject(subjectEntity);
        subject.setNo(buildStudentNo(count==0?1:count+1));
        subjectDao.save(subject);
        return SubjectUtil.toSubjectEntity(subject);
    }

    @Override
    public QueryResultEntity<List<SubjectEntity>> query(SubjectQueryParam subjectQueryParam) {
        List<Subject> subjects = subjectDao.query(subjectQueryParam);
        Integer count = subjectDao.queryCount(subjectQueryParam);
        List<SubjectEntity> subjectEntities = new ArrayList<>();
        subjects.forEach(o -> subjectEntities.add(SubjectUtil.toSubjectEntity(o)));
        QueryResultEntity<List<SubjectEntity>> queryResultEntity = new QueryResultEntity<>(subjectQueryParam.getPageSize(), count);
        queryResultEntity.setData(subjectEntities);
        queryResultEntity.setCode("00");
        queryResultEntity.setResult("success");
        return queryResultEntity;
    }

    @Override
    public SubjectEntity updateSubject(SubjectEntity subjectEntity) {
        Subject subject = SubjectUtil.toSubject(subjectEntity);
        subjectDao.update(subject);
        return SubjectUtil.toSubjectEntity(subject);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectDao.delete(id);
    }

    private static String buildStudentNo( Integer id) {
        return "1001"+String.format("%03d", id);
    }
}
