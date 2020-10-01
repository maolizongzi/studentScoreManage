package org.graduate.student.service.impl;

import org.graduate.base.general.utility.AESUtil;
import org.graduate.student.repository.dao.StudentDao;
import org.graduate.student.repository.model.Student;
import org.graduate.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void addStudent(Student student) {
        try {
            student.setPassword(AESUtil.encrypt(passwordKey, student.getIdentityNo()));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        studentDao.save(student);
        student.setNo(buildStudentNo(student.getAdmissionDate(), student.getId(), student.getClassesId()));
        studentDao.update(student);
    }


    private String buildStudentNo(Date admissionDate, Long id, Long classesId) {
        return "S" +
                new SimpleDateFormat("yyyyMMdd").format(admissionDate) +
                String.format("%02d%n", classesId) +
                String.format("%03d%n", id);
    }
}
