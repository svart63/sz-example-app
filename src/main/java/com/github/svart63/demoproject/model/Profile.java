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
    private long birthDay;
    @OneToMany
    private List<Profile> friends;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
