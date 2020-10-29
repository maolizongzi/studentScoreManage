package org.graduate.teacher.controller;

import org.graduate.base.general.entity.BaseResultEntity;
import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.role.repository.model.RolePermissionQueryParam;
import org.graduate.role.service.RolePermissionService;
import org.graduate.role.service.entity.RolePermissionEntity;
import org.graduate.teacher.repository.model.TeacherQueryParam;
import org.graduate.teacher.service.TeacherService;
import org.graduate.teacher.service.entity.TeacherEntity;
import org.graduate.teacher.service.entity.TeacherLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final RolePermissionService rolePermissionService;

    @Autowired
    public TeacherController(TeacherService teacherService, RolePermissionService rolePermissionService) {
        this.teacherService = teacherService;
        this.rolePermissionService = rolePermissionService;
    }

    @PostMapping("login")
    public TeacherLoginEntity login(@RequestBody TeacherEntity teacherEntity) {
        TeacherLoginEntity teacherLoginEntity = teacherService.loginByTeacherNo(teacherEntity.getNo(), teacherEntity.getPassword());
        if (teacherLoginEntity.getCode().equals("00")) {
            RolePermissionQueryParam entity = new RolePermissionQueryParam(1, 100);
            entity.setRoleId(teacherLoginEntity.getData().getRoleId());
            QueryResultEntity<List<RolePermissionEntity>> rolePermissionQueryResultEntity = rolePermissionService.query(entity);
            List<String> permissions = new ArrayList<>();
            rolePermissionQueryResultEntity.getData().forEach(o -> permissions.add(o.getPermission()));
            teacherLoginEntity.getData().setPermissions(permissions);
        }
        return teacherLoginEntity;
    }

    @PostMapping("register")
    public BaseResultEntity<TeacherEntity> register(@RequestBody TeacherEntity teacherEntity) {
        BaseResultEntity<TeacherEntity> resultEntity = new BaseResultEntity<>();
        resultEntity.setCode("01");
        resultEntity.setResult("新增失败");
        try {
            TeacherEntity resultTeacher = teacherService.addTeacher(teacherEntity);
            resultEntity.setCode("00");
            resultEntity.setResult("新增成功");
            resultEntity.setData(resultTeacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultEntity;
    }

    @PostMapping("update")
    public BaseResultEntity<TeacherEntity> update(@RequestBody TeacherEntity teacherEntity) {
        BaseResultEntity<TeacherEntity> resultEntity = new BaseResultEntity<>();
        resultEntity.setCode("01");
        resultEntity.setResult("新增失败");
        try {
            TeacherEntity resultTeacher = teacherService.updateTeacher(teacherEntity);
            resultEntity.setCode("00");
            resultEntity.setResult("新增成功");
            resultEntity.setData(resultTeacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultEntity;
    }

    @GetMapping("query")
    public QueryResultEntity<List<TeacherEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "no", required = false) String no,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gender", required = false) Integer gender,
            @RequestParam(value = "telephone", required = false) String telephone,
            @RequestParam(value = "admissionDate", required = false) Date admissionDate,
            @RequestParam(value = "resignDate", required = false) Date resignDate,
            @RequestParam(value = "status", required = false) Integer status
    ) {
        TeacherQueryParam teacherQueryParam = new TeacherQueryParam(currentPage, pageSize);
        teacherQueryParam.setId(id);
        teacherQueryParam.setNo(no);
        teacherQueryParam.setName(name);
        teacherQueryParam.setGender(gender);
        teacherQueryParam.setTelephone(telephone);
        teacherQueryParam.setAdmissionDate(admissionDate);
        teacherQueryParam.setResignDate(resignDate);
        teacherQueryParam.setStatus(status);
        return teacherService.query(teacherQueryParam);
    }
}
