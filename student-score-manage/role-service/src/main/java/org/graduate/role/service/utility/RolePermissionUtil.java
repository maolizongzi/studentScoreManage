package org.graduate.role.service.utility;

import org.graduate.role.repository.model.RolePermission;
import org.graduate.role.service.entity.RolePermissionEntity;
import org.springframework.beans.BeanUtils;

public class RolePermissionUtil {
    public static RolePermission toRolePermission(RolePermissionEntity rolePermissionEntity) {
        RolePermission rolePermission = new RolePermission();
        if (rolePermissionEntity != null) {
            BeanUtils.copyProperties(rolePermissionEntity, rolePermission);
        }
        return rolePermission;
    }

    public static RolePermissionEntity toRolePermissionEntity(RolePermission rolePermission) {
        RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
        if (rolePermission != null) {
            BeanUtils.copyProperties(rolePermission, rolePermissionEntity);
        }
        return rolePermissionEntity;

    }
}
