package org.graduate.student.service.utility;

import org.graduate.student.repository.model.Student;
import org.graduate.student.service.entity.StudentEntity;
import org.springframework.beans.BeanUtils;

public class StudentUtil {

    public static StudentEntity toStudentEntity(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(student, studentEntity);
        return studentEntity;
    }

    public static Student toStudent(StudentEntity studentEntity) {
        Student student = new Student();
        BeanUtils.copyProperties(studentEntity, student);
        return student;
    }
}
