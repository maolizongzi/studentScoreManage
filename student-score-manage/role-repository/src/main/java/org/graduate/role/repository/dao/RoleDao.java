package org.graduate.role.repository.dao;

import org.graduate.role.repository.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao {
    void save(Role role);
}
