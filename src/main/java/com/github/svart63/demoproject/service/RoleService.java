package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.Role;
import com.github.svart63.demoproject.repo.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService extends BaseService<Role, RoleRepository> {
    public RoleService(RoleRepository repo) {
        super(repo);
    }

    public Optional<Role> findByName(String user) {
        return repo.findByName(user);
    }
}
