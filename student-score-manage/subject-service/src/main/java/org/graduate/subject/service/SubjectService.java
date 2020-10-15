package org.graduate.subject.service;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.subject.repository.model.SubjectQueryParam;
import org.graduate.subject.service.entity.SubjectEntity;

import java.util.List;

public interface SubjectService {

    SubjectEntity addSubject(SubjectEntity subjectEntity);

    QueryResultEntity<List<SubjectEntity>> query(SubjectQueryParam subjectQueryParam);

    SubjectEntity updateSubject(SubjectEntity subjectEntity);
}
