package com.github.svart63.demoproject.repo.auth;

import com.github.svart63.demoproject.model.UserLogin;
import com.github.svart63.demoproject.repo.auth.AuthRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends AuthRepository {
    boolean existsByEmail(String email);
}
