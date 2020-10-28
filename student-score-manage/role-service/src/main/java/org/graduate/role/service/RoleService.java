package org.graduate.role.service;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.role.repository.model.RoleQueryParam;
import org.graduate.role.service.entity.RoleEntity;

import java.util.List;

public interface RoleService {
    RoleEntity saveRole(RoleEntity roleEntity);

    RoleEntity updateRole(RoleEntity roleEntity);

    QueryResultEntity<List<RoleEntity>> query(RoleQueryParam roleQueryParam);


}
