package com.github.svart63.demoproject.repo.auth;

import com.github.svart63.demoproject.model.UserLogin;
import com.github.svart63.demoproject.repo.BaseRepo;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface AuthRepository extends BaseRepo<UserLogin> {
    Optional<UserLogin> findByEmail(String email);
}
