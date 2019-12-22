package com.github.svart63.demoproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Profile extends BaseObject {
    private String firstName;
    private String lastName;
    private int age;
    private String status;
    @Formula("select p.firstName, p.lastName, p.id from profile p where p.id=id")
    @OneToMany
    private List<Profile> friends;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserLogin userLogin;

}
