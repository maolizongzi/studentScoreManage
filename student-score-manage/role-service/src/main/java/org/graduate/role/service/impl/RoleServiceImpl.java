package org.graduate.role.service.impl;

import org.graduate.base.general.entity.QueryResultEntity;
import org.graduate.role.repository.dao.RoleDao;
import org.graduate.role.repository.model.Role;
import org.graduate.role.repository.model.RoleQueryParam;
import org.graduate.role.service.RoleService;
import org.graduate.role.service.entity.RoleEntity;
import org.graduate.role.service.utility.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;


    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public RoleEntity saveRole(RoleEntity roleEntity) {
        Role role = RoleUtil.toRole(roleEntity);
        roleDao.save(role);
        return RoleUtil.toRoleEntity(role);
    }

    @Override
    public RoleEntity updateRole(RoleEntity roleEntity) {
        Role role = RoleUtil.toRole(roleEntity);
        roleDao.update(role);
        return RoleUtil.toRoleEntity(role);
    }

    @Override
    public QueryResultEntity<List<RoleEntity>> query(RoleQueryParam roleQueryParam) {
        List<Role> roles = roleDao.query(roleQueryParam);
        List<RoleEntity> roleEntities = new ArrayList<>();
        roles.forEach(o -> roleEntities.add(RoleUtil.toRoleEntity(o)));
        Integer count = roleDao.queryCount(roleQueryParam);
        QueryResultEntity<List<RoleEntity>> queryResultEntity = new QueryResultEntity<>(roleQueryParam.getPageSize(), count);
        queryResultEntity.setCode("00");
        queryResultEntity.setResult("success");
        queryResultEntity.setData(roleEntities);
        return queryResultEntity;
    }
}
