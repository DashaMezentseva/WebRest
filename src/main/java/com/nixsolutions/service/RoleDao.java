package com.nixsolutions.service;

import com.nixsolutions.domain.Role;
import java.util.List;

public interface RoleDao {
    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);

    List<Role> findAll();

    Role findById(Long id);
}
