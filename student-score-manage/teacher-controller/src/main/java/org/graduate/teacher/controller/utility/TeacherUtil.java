package org.graduate.teacher.controller.utility;

import org.graduate.teacher.repository.model.Teacher;
import org.graduate.teacher.service.entity.TeacherEntity;
import org.springframework.beans.BeanUtils;

public class TeacherUtil {
    public static TeacherEntity toTeacherEntity(Teacher teacher) {
        TeacherEntity teacherEntity = new TeacherEntity();
        BeanUtils.copyProperties(teacher, teacherEntity);
        return teacherEntity;
    }

    public static Teacher toTeacher(TeacherEntity teacherEntity) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherEntity, teacher);
        return teacher;

    }
}
