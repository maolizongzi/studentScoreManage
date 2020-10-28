package org.graduate.role.service.impl;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.role.repository.dao.RolePermissionsDao;
import org.graduate.role.repository.model.RolePermission;
import org.graduate.role.repository.model.RolePermissionQueryParam;
import org.graduate.role.service.RolePermissionService;
import org.graduate.role.service.entity.RolePermissionEntity;
import org.graduate.role.service.utility.RolePermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionsDao rolePermissionsDao;

    @Autowired
    public RolePermissionServiceImpl(RolePermissionsDao rolePermissionsDao) {
        this.rolePermissionsDao = rolePermissionsDao;
    }

    @Override
    public RolePermissionEntity save(RolePermissionEntity rolePermissionEntity) {
        RolePermission rolePermission = RolePermissionUtil.toRolePermission(rolePermissionEntity);
        rolePermissionsDao.save(rolePermission);
        return RolePermissionUtil.toRolePermissionEntity(rolePermission);
    }

    @Override
    public RolePermissionEntity update(RolePermissionEntity rolePermissionEntity) {
        RolePermission rolePermission = RolePermissionUtil.toRolePermission(rolePermissionEntity);
        rolePermissionsDao.update(rolePermission);
        return RolePermissionUtil.toRolePermissionEntity(rolePermission);
    }

    @Override
    public QueryResultEntity<List<RolePermissionEntity>> query(RolePermissionQueryParam rolePermissionQueryParam) {
        List<RolePermission> rolePermissions = rolePermissionsDao.query(rolePermissionQueryParam);
        Integer count = rolePermissionsDao.queryCount(rolePermissionQueryParam);
        List<RolePermissionEntity> rolePermissionEntities = new ArrayList<>();
        rolePermissions.forEach(o -> rolePermissionEntities.add(RolePermissionUtil.toRolePermissionEntity(o)));
        QueryResultEntity<List<RolePermissionEntity>> queryResultEntity = new QueryResultEntity<>(rolePermissionQueryParam.getPageSize(), count);
        queryResultEntity.setCode("00");
        queryResultEntity.setResult("success");
        queryResultEntity.setData(rolePermissionEntities);
        return queryResultEntity;
    }
}
