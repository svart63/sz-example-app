package com.github.svart63.demoproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Friendship extends BaseObject {
    @OneToOne(cascade = CascadeType.REMOVE)
    private Profile profile;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Profile friend;
}
