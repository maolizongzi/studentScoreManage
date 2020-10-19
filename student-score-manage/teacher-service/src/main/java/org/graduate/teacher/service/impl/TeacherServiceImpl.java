package org.graduate.teacher.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.base.general.utility.AESUtil;
import org.graduate.teacher.repository.dao.TeacherDao;
import org.graduate.teacher.repository.model.Teacher;
import org.graduate.teacher.repository.model.TeacherQueryParam;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public TeacherEntity addTeacher(TeacherEntity teacherEntity) {
        Teacher teacher = TeacherUtil.toTeacher(teacherEntity);
        String password = teacher.getTelephone();
        if (StringUtils.isNotEmpty(teacher.getPassword())) {
            password = teacher.getPassword();
        }
        try {
            teacher.setPassword(AESUtil.encrypt(passwordKey, password));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        teacherDao.save(teacher);
        teacher.setNo(buildTeacherNo(teacher.getId(), teacher.getAdmissionDate()));
        teacherDao.update(teacher);
        return TeacherUtil.toTeacherEntity(teacher);
    }

    @Override
    public TeacherEntity updateTeacher(TeacherEntity teacherEntity) {
        Teacher teacher = TeacherUtil.toTeacher(teacherEntity);
        if (StringUtils.isNotEmpty(teacher.getPassword())) {
            try {
                teacher.setPassword(AESUtil.encrypt(passwordKey, teacher.getPassword()));
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        teacherDao.update(teacher);
        return TeacherUtil.toTeacherEntity(teacher);
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
            teacherLoginEntity.setData(teacherEntity);
        }
        return teacherLoginEntity;
    }

    @Override
    public QueryResultEntity<List<TeacherEntity>> query(TeacherQueryParam teacherQueryParam) {
        List<Teacher> teachers = teacherDao.query(teacherQueryParam);
        Integer count = teacherDao.queryCount(teacherQueryParam);

        List<TeacherEntity> teacherEntities = new ArrayList<>();
        teachers.forEach(o -> teacherEntities.add(TeacherUtil.toTeacherEntity(o)));

        QueryResultEntity<List<TeacherEntity>> queryResultEntity = new QueryResultEntity<>(teacherQueryParam.getPageSize(), count);
        queryResultEntity.setData(teacherEntities);
        queryResultEntity.setCode("00");
        queryResultEntity.setResult("success");
        return queryResultEntity;
    }


    private String buildTeacherNo(Long id, Date admissionDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        return "T" +
                simpleDateFormat.format(admissionDate) +
                id;
    }
}
