package com.github.svart63.demoproject.repo.auth;

import com.github.svart63.demoproject.model.UserLogin;
import com.github.svart63.demoproject.repo.BaseRepo;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuthRepository extends BaseRepo<UserLogin> {
    UserLogin findByEmail(String email);
}
