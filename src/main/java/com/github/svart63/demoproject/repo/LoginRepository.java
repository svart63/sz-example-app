package com.github.svart63.demoproject.repo;

import com.github.svart63.demoproject.model.UserLogin;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends BaseRepo<UserLogin> {
}
