package com.github.svart63.demoproject.repo.auth;

import com.github.svart63.demoproject.model.UserLogin;
import com.github.svart63.demoproject.repo.BaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends BaseRepo<UserLogin>, AuthRepository {
}
