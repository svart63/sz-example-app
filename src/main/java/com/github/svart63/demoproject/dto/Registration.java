package com.github.svart63.demoproject.dto;

import lombok.Data;

@Data
public class Registration {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private long birthDay;
}
