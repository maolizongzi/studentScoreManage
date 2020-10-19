package org.graduate.student.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.base.general.utility.AESUtil;
import org.graduate.student.repository.dao.StudentDao;
import org.graduate.student.repository.model.Student;
import org.graduate.student.repository.model.StudentQueryParam;
import org.graduate.student.service.StudentService;
import org.graduate.student.service.entity.StudentEntity;
import org.graduate.student.service.utility.StudentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    @Value("${graduate.password.key}")
    private String passwordKey;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public StudentEntity addStudent(StudentEntity studentEntity) {
        Student student = StudentUtil.toStudent(studentEntity);
        try {
            student.setPassword(AESUtil.encrypt(passwordKey, student.getIdentityNo()));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        studentDao.save(student);
        student.setNo(buildStudentNo(student.getAdmissionDate(), student.getId(), student.getClassesId()));
        studentDao.update(student);
        return StudentUtil.toStudentEntity(student);
    }

    @Override
    public StudentEntity updateStudent(StudentEntity studentEntity) {
        Student student = StudentUtil.toStudent(studentEntity);
        if (StringUtils.isNotEmpty(student.getPassword())) {
            try {
                student.setPassword(AESUtil.encrypt(passwordKey, student.getPassword()));
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        studentDao.update(student);
        return StudentUtil.toStudentEntity(student);
    }

    @Override
    public QueryResultEntity<List<StudentEntity>> query(StudentQueryParam studentQueryParam) {
        List<Student> students = studentDao.query(studentQueryParam);
        List<StudentEntity> studentEntities = new ArrayList<>();
        students.forEach(o -> studentEntities.add(StudentUtil.toStudentEntity(o)));
        Integer count = studentDao.queryCount(studentQueryParam);
        QueryResultEntity<List<StudentEntity>> queryResultEntity = new QueryResultEntity<>(studentQueryParam.getCurrentPage(), count);
        queryResultEntity.setCode("00");
        queryResultEntity.setResult("success");
        queryResultEntity.setData(studentEntities);
        return queryResultEntity;
    }


    private String buildStudentNo(Date admissionDate, Long id, Long classesId) {
        return "S" +
                new SimpleDateFormat("yyyyMMdd").format(admissionDate) +
                String.format("%02d", classesId) +
                String.format("%03d", id);
    }
}