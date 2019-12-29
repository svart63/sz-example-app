package com.github.svart63.demoproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.svart63.demoproject.service.ProfileService;
import com.github.svart63.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ProfileService profileService;
    @Autowired
    protected UserService userService;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    protected byte[] json(Object o) throws JsonProcessingException {
        return MAPPER.writeValueAsBytes(o);
    }
}
