package org.graduate.role.service;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.role.repository.model.RolePermissionQueryParam;
import org.graduate.role.service.entity.RolePermissionEntity;

import java.util.List;

public interface RolePermissionService {

    RolePermissionEntity save(RolePermissionEntity rolePermissionEntity);

    RolePermissionEntity update(RolePermissionEntity rolePermissionEntity);

    QueryResultEntity<List<RolePermissionEntity>> query(RolePermissionQueryParam rolePermissionQueryParam);
}
