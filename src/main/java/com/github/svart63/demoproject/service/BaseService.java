package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.BaseObject;
import com.github.svart63.demoproject.repo.BaseRepo;
import lombok.Setter;

import javax.transaction.Transactional;
import java.util.Optional;

@Setter
@Transactional
public abstract class BaseService<E extends BaseObject, R extends BaseRepo<E>> {
    protected R repo;

    public BaseService(R repo) {
        this.repo = repo;
    }

    public Optional<E> findOne(long id) {
        return repo.findById(id);
    }

    public E save(E object) {
        return repo.save(object);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public void delete(E object) {
        repo.delete(object);
    }
}
