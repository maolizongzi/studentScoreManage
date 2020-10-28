package org.graduate.role.service.impl;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.role.repository.dao.PermissionDao;
import org.graduate.role.repository.model.Permission;
import org.graduate.role.repository.model.PermissionQueryParam;
import org.graduate.role.service.PermissionService;
import org.graduate.role.service.entity.PermissionEntity;
import org.graduate.role.service.utility.PermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionDao permissionDao;

    @Autowired
    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public PermissionEntity save(PermissionEntity permissionEntity) {
        Permission permission = PermissionUtil.toPermissions(permissionEntity);
        permissionDao.save(permission);
        return PermissionUtil.toPermissionEntity(permission);
    }

    @Override
    public PermissionEntity update(PermissionEntity permissionEntity) {
        Permission permission = PermissionUtil.toPermissions(permissionEntity);
        permissionDao.update(permission);
        return PermissionUtil.toPermissionEntity(permission);
    }

    @Override
    public QueryResultEntity<List<PermissionEntity>> query(PermissionQueryParam permissionQueryParam) {
        List<Permission> permissions = permissionDao.query(permissionQueryParam);
        Integer count = permissionDao.queryCount(permissionQueryParam);
        List<PermissionEntity> permissionEntities = new ArrayList<>();
        permissions.forEach(o -> permissionEntities.add(PermissionUtil.toPermissionEntity(o)));
        QueryResultEntity<List<PermissionEntity>> queryResultEntity = new QueryResultEntity<>(permissionQueryParam.getPageSize(), count);
        queryResultEntity.setCode("00");
        queryResultEntity.setResult("success");
        queryResultEntity.setData(permissionEntities);
        return queryResultEntity;
    }
}
