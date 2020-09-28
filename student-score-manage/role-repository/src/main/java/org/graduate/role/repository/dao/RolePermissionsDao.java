package org.graduate.role.repository.dao;

import org.graduate.role.repository.model.RolePermissions;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionsDao {
    void save(RolePermissions rolePermissions);
}
