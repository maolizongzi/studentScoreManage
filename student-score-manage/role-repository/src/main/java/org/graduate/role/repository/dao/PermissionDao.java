package org.graduate.role.repository.dao;

import org.graduate.role.repository.model.Permission;
import org.graduate.role.repository.model.PermissionQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    void save(Permission permissions);

    void update(Permission permission);

    List<Permission> query(PermissionQueryParam permissionQueryParam);

    Integer queryCount(PermissionQueryParam permissionQueryParam);
}
