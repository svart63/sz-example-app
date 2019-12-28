package com.github.svart63.demoproject.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
