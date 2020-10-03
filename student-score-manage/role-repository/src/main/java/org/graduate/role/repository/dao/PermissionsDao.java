package org.graduate.role.repository.dao;

import org.graduate.role.repository.model.Permissions;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsDao {
    void save(Permissions permissions);
}
