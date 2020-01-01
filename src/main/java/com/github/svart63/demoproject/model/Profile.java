package com.github.svart63.demoproject.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
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

    public Profile(String firstName, String lastName, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setId(id);
    }
}
