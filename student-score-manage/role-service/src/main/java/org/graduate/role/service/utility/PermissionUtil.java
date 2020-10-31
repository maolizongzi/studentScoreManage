package org.graduate.role.service.utility;

import org.graduate.role.repository.model.Permission;
import org.graduate.role.service.entity.PermissionEntity;
import org.springframework.beans.BeanUtils;

public class PermissionUtil {
    public static Permission toPermissions(PermissionEntity permissionEntity) {
        Permission permissions = new Permission();
        if (permissionEntity != null) {
            BeanUtils.copyProperties(permissionEntity, permissions);
        }
        return permissions;
    }

    public static PermissionEntity toPermissionEntity(Permission permission) {
        PermissionEntity permissionEntity = new PermissionEntity();
        if (permission != null) {
            BeanUtils.copyProperties(permission, permissionEntity);
        }
        return permissionEntity;
    }
}
