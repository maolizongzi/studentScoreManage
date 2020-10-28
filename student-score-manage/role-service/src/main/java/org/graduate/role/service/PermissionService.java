package org.graduate.role.service;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.role.repository.model.PermissionQueryParam;
import org.graduate.role.service.entity.PermissionEntity;

import java.util.List;

public interface PermissionService {
    PermissionEntity save(PermissionEntity permissionEntity);

    PermissionEntity update(PermissionEntity permissionEntity);

    QueryResultEntity<List<PermissionEntity>> query(PermissionQueryParam permissionQueryParam);
}
