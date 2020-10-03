package org.graduate.teacher.service.impl;

import org.graduate.base.general.utility.AESUtil;
import org.graduate.teacher.repository.dao.TeacherDao;
import org.graduate.teacher.repository.model.Teacher;
import org.graduate.teacher.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {


    private final TeacherDao teacherDao;
    @Value("${graduate.password.key}")
    private String passwordKey;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        try {
            teacher.setPassword(AESUtil.encrypt(passwordKey, teacher.getTelephone()));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        teacherDao.save(teacher);
        teacher.setNo(buildTeacherNo(teacher.getId(), teacher.getAdmissionDate()));
        teacherDao.update(teacher);
    }

    private String buildTeacherNo(Long id, Date admissionDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        return "T" +
                simpleDateFormat.format(admissionDate) +
                id;
    }
}
