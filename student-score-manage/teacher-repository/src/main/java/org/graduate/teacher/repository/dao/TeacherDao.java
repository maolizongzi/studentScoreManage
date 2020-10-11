package org.graduate.teacher.repository.dao;

import org.apache.ibatis.annotations.Param;
import org.graduate.teacher.repository.model.Teacher;
import org.graduate.teacher.repository.model.TeacherQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {

    void save(Teacher teacher);

    void update(Teacher teacher);

    Teacher queryByNo(@Param("no") String no);

    List<Teacher> query(TeacherQueryParam teacherQueryParam);

    Integer queryCount(TeacherQueryParam teacherQueryParam);
}
