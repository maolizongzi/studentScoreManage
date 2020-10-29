package org.graduate.teacher.controller;

import org.graduate.base.general.entity.BaseResultEntity;
import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.role.repository.model.PermissionQueryParam;
import org.graduate.role.service.PermissionService;
import org.graduate.role.service.RolePermissionService;
import org.graduate.role.service.entity.PermissionEntity;
import org.graduate.role.service.entity.RolePermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role/permission")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;
    private final PermissionService permissionService;

    @Autowired
    public RolePermissionController(RolePermissionService rolePermissionService, PermissionService permissionService) {
        this.rolePermissionService = rolePermissionService;
        this.permissionService = permissionService;
    }

    @PostMapping("add")
    public BaseResultEntity<RolePermissionEntity> add(@RequestBody RolePermissionEntity rolePermissionEntity) {
        BaseResultEntity<RolePermissionEntity> baseResultEntity = new BaseResultEntity<>();
        baseResultEntity.setCode("01");
        baseResultEntity.setResult("fail");
        try {
            if (rolePermissionEntity.getPermissions() != null && rolePermissionEntity.getPermissions().size() > 0) {
                rolePermissionEntity.getPermissions().forEach(o -> {

                    PermissionQueryParam queryEntity = new PermissionQueryParam(1, 1);
                    queryEntity.setPermission(o);
                    QueryResultEntity<List<PermissionEntity>> queryResultEntity = permissionService.query(queryEntity);
                    PermissionEntity permission = queryResultEntity.getData().get(0);

                    RolePermissionEntity saveEntity = new RolePermissionEntity();
                    saveEntity.setRoleId(rolePermissionEntity.getRoleId());
                    saveEntity.setPermissionId(permission.getId());
                    rolePermissionService.save(saveEntity);
                });
            }
            baseResultEntity.setCode("00");
            baseResultEntity.setResult("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseResultEntity;
    }

}
