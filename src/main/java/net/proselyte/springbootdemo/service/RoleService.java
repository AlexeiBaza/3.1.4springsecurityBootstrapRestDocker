package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRoleName(String roleName);

    void create(Role roleUser);
}
