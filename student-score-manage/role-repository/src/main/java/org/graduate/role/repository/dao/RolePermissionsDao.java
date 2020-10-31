package org.graduate.role.repository.dao;

import org.graduate.role.repository.model.RolePermission;
import org.graduate.role.repository.model.RolePermissionQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionsDao {
    void save(RolePermission rolePermissions);

    void update(RolePermission rolePermission);

    List<RolePermission> query(RolePermissionQueryParam rolePermissionQueryParam);

    Integer queryCount(RolePermissionQueryParam rolePermissionQueryParam);
}
