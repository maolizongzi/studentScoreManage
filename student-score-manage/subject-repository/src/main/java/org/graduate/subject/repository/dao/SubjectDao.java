package org.graduate.subject.repository.dao;

import org.graduate.subject.repository.model.Subject;
import org.graduate.subject.repository.model.SubjectQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDao {
    void save(Subject subject);

    List<Subject> query(SubjectQueryParam subjectQueryParam);

    Integer queryCount(SubjectQueryParam subjectQueryParam);

}
