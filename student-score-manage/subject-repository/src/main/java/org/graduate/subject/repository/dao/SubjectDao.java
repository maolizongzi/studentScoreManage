package org.graduate.subject.repository.dao;

import org.graduate.subject.repository.model.Subject;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao {
    void save(Subject subject);
}
