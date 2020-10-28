package org.graduate.role.repository.model;

import org.graduate.base.general.entity.BaseQueryParam;

public class PermissionQueryParam extends BaseQueryParam {
    public PermissionQueryParam(Integer currentPage, Integer pageSize) {
        super(currentPage, pageSize);
    }
    private Long id;
    private String name;
    private String permission;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
