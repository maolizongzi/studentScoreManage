package org.graduate.teacher.controller;

import org.graduate.base.general.entity.BaseResultEntity;
import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.role.repository.model.RoleQueryParam;
import org.graduate.role.service.RoleService;
import org.graduate.role.service.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("add")
    public BaseResultEntity<RoleEntity> add(@RequestBody RoleEntity roleEntity) {
        BaseResultEntity<RoleEntity> baseResultEntity = new BaseResultEntity<>();
        baseResultEntity.setCode("01");
        baseResultEntity.setResult("fail");
        try {
            RoleEntity resultStudentEntity = roleService.saveRole(roleEntity);
            baseResultEntity.setCode("00");
            baseResultEntity.setResult("success");
            baseResultEntity.setData(resultStudentEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseResultEntity;
    }

    @PostMapping("edit")
    public BaseResultEntity<RoleEntity> edit(@RequestBody RoleEntity roleEntity) {
        BaseResultEntity<RoleEntity> baseResultEntity = new BaseResultEntity<>();
        baseResultEntity.setCode("01");
        baseResultEntity.setResult("fail");
        try {
            RoleEntity resultStudentEntity = roleService.updateRole(roleEntity);
            baseResultEntity.setCode("00");
            baseResultEntity.setResult("success");
            baseResultEntity.setData(resultStudentEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseResultEntity;
    }

    @GetMapping("query")
    public QueryResultEntity<List<RoleEntity>> query(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name
    ) {
        RoleQueryParam roleQueryParam = new RoleQueryParam(currentPage, pageSize);
        roleQueryParam.setId(id);
        roleQueryParam.setName(name);
        return roleService.query(roleQueryParam);
    }
}
