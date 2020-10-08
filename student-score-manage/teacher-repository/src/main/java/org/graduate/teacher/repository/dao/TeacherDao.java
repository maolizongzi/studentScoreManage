package org.graduate.teacher.repository.dao;

import org.apache.ibatis.annotations.Param;
import org.graduate.teacher.repository.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao {
    void save(Teacher teacher);

    void update(Teacher teacher);

    Teacher queryByNo(@Param("no") String no);
}
