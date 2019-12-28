package com.github.svart63.demoproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseObject {
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private transient Role role;
}
