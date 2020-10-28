package org.graduate.role.service.utility;

import org.graduate.role.repository.model.Role;
import org.graduate.role.service.entity.RoleEntity;
import org.springframework.beans.BeanUtils;

public class RoleUtil {
    public static Role toRole(RoleEntity roleEntity) {
        Role role = new Role();
        if (roleEntity != null) {
            BeanUtils.copyProperties(roleEntity, role);
        }
        return role;
    }


    public static RoleEntity toRoleEntity(Role role) {
        RoleEntity roleEntity = new RoleEntity();
        if (role != null) {
            BeanUtils.copyProperties(role, roleEntity);
        }

        return roleEntity;
    }
}
