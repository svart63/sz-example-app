package com.github.svart63.demoproject.repo.auth;

import com.github.svart63.demoproject.model.User;
import com.github.svart63.demoproject.repo.BaseRepo;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface AuthRepository extends BaseRepo<User> {
    Optional<User> findByEmail(String email);
}
