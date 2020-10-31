package org.graduate.role.repository.model;

import org.graduate.base.general.entity.BaseQueryParam;

public class RoleQueryParam extends BaseQueryParam {

    public RoleQueryParam(Integer currentPage, Integer pageSize) {
        super(currentPage, pageSize);
    }

    private Long id;
    private String name;

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
