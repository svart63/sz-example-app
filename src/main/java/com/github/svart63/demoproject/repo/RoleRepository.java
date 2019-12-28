package com.github.svart63.demoproject.repo;

import com.github.svart63.demoproject.model.Role;

import java.util.Optional;

public interface RoleRepository extends BaseRepo<Role> {
    Optional<Role> findByName(String user);
}
