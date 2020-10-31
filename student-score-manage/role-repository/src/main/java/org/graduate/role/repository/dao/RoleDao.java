package org.graduate.role.repository.dao;

import org.graduate.role.repository.model.Role;
import org.graduate.role.repository.model.RoleQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    
    void save(Role role);

    void update(Role role);

    List<Role> query(RoleQueryParam roleQueryParam);

    Integer queryCount(RoleQueryParam roleQueryParam);
}
