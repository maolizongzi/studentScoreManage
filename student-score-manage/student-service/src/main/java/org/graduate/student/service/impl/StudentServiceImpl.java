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
        //查询当前届学生count，count+1进行学生编号
        StudentQueryParam q=new StudentQueryParam();
        q.setClassesNo(student.getClassesNo());
        Integer studentCount= studentDao.queryCount(q);

        student.setNo(buildStudentNo(student.getAdmissionDate(),  studentCount, student.getClassesNo()));
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
        }else {
            StudentQueryParam param=new StudentQueryParam(1,1);
            param.setClassesNo(student.getClassesNo());
            param.setNo(student.getNo());
            List<Student>  studentList= studentDao.query(param);
            student.setPassword(studentList.get(0).getPassword());
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
        QueryResultEntity<List<StudentEntity>> queryResultEntity = new QueryResultEntity<>(studentQueryParam.getPageSize(), count);
        queryResultEntity.setCode("00");
        queryResultEntity.setResult("success");
        queryResultEntity.setData(studentEntities);
        return queryResultEntity;
    }


    private static String buildStudentNo(Date admissionDate, Integer id, String classesNo) {
        String temp=classesNo.substring(4);
        return "S" +
                new SimpleDateFormat("yyyy0901").format(admissionDate) +
                temp +
                String.format("%03d", id);
    }
}