package org.graduate.teacher.service.impl;

import org.graduate.base.general.utility.AESUtil;
import org.graduate.teacher.repository.dao.TeacherDao;
import org.graduate.teacher.repository.model.Teacher;
import org.graduate.teacher.service.TeacherService;
import org.graduate.teacher.service.entity.TeacherEntity;
import org.graduate.teacher.service.entity.TeacherLoginEntity;
import org.graduate.teacher.service.utils.TeacherUtil;
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
    public void addTeacher(TeacherEntity teacherEntity) {
        Teacher teacher = TeacherUtil.toTeacher(teacherEntity);
        try {
            teacher.setPassword(AESUtil.encrypt(passwordKey, teacher.getTelephone()));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        teacherDao.save(teacher);
        teacher.setNo(buildTeacherNo(teacher.getId(), teacher.getAdmissionDate()));
        teacherDao.update(teacher);
    }

    @Override
    public TeacherLoginEntity loginByTeacherNo(String teacherNo, String password) {
        Teacher teacher = teacherDao.queryByNo(teacherNo);
        String encryptedPassword = "";
        try {
            encryptedPassword = AESUtil.encrypt(passwordKey, password);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }

        TeacherLoginEntity teacherLoginEntity = new TeacherLoginEntity();
        teacherLoginEntity.setCode("err");
        teacherLoginEntity.setResult("用户名或尼玛错误");
        if (encryptedPassword.equals(teacher.getPassword())) {
            teacherLoginEntity.setCode("00");
            teacherLoginEntity.setResult("success");
            TeacherEntity teacherEntity = TeacherUtil.toTeacherEntity(teacher);
            teacherLoginEntity.setTeacherEntity(teacherEntity);
        }


        return teacherLoginEntity;
    }


    private String buildTeacherNo(Long id, Date admissionDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        return "T" +
                simpleDateFormat.format(admissionDate) +
                id;
    }
}
