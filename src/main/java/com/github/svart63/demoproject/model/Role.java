package com.github.svart63.demoproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Role extends BaseObject {
    @Column(unique = true)
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
