package com.github.svart63.demoproject.repo.auth;

import com.github.svart63.demoproject.model.User;
import com.github.svart63.demoproject.repo.BaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepo<User>, AuthRepository {
}
