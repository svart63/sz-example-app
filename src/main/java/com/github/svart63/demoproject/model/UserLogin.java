package com.github.svart63.demoproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class UserLogin extends BaseObject {
    @Column(unique = true)
    private String email;
    private String password;
}
