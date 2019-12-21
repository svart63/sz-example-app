package com.github.svart63.demoproject.repo;

import com.github.svart63.demoproject.model.BaseObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepo<T extends BaseObject> extends CrudRepository<T, Long> {
}
