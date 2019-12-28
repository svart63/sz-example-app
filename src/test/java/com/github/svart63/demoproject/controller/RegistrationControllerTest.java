package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testRegistrationControllerSuccess() throws Exception {
        User login = new User();
        login.setEmail("123@domain.com");
        login.setPassword("12345678");
        mockMvc.perform(post("/registration", login));
    }
}