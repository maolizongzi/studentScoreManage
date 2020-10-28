package org.graduate.role.repository.model;

import org.graduate.base.general.entity.BaseQueryParam;

public class RolePermissionQueryParam extends BaseQueryParam {
    public RolePermissionQueryParam(Integer currentPage, Integer pageSize) {
        super(currentPage, pageSize);

    }

    private Long id;
    private Long roleId;
    private Long permissionsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(Long permissionsId) {
        this.permissionsId = permissionsId;
    }
}
