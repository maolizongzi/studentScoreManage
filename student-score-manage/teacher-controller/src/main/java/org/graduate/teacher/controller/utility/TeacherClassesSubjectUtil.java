package org.graduate.teacher.controller.utility;

import org.graduate.teacher.repository.model.TeacherClassesSubject;
import org.graduate.teacher.service.entity.TeacherClassesSubjectEntity;
import org.springframework.beans.BeanUtils;

public class TeacherClassesSubjectUtil {

    public static TeacherClassesSubjectEntity toTeacherClassesSubjectEntity(TeacherClassesSubject teacherClassesSubject) {
        TeacherClassesSubjectEntity teacherClassesSubjectEntity = new TeacherClassesSubjectEntity();
        BeanUtils.copyProperties(teacherClassesSubject, teacherClassesSubjectEntity);
        return teacherClassesSubjectEntity;
    }

    public static TeacherClassesSubject toTeacherClassesSubject(TeacherClassesSubjectEntity teacherClassesSubjectEntity) {
        TeacherClassesSubject teacherClassesSubject = new TeacherClassesSubject();
        BeanUtils.copyProperties(teacherClassesSubjectEntity, teacherClassesSubject);
        return teacherClassesSubject;
    }
}
