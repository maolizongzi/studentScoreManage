package org.graduate.subject.service.utility;

import org.graduate.subject.repository.model.Subject;
import org.graduate.subject.service.entity.SubjectEntity;
import org.springframework.beans.BeanUtils;

public class SubjectUtil {

    public static Subject toSubject(SubjectEntity subjectEntity) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectEntity, subject);
        return subject;
    }

    public static SubjectEntity toSubjectEntity(Subject subject) {
        SubjectEntity subjectEntity = new SubjectEntity();
        BeanUtils.copyProperties(subject, subjectEntity);
        return subjectEntity;
    }
}
