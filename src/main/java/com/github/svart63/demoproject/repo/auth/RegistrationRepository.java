package com.github.svart63.demoproject.repo.auth;

import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends AuthRepository {
    boolean existsByEmail(String email);
}
