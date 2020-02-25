package com.laqr.NewspaperDeliverySystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUserCredentials() throws Exception {
        mvc.perform(post("/login")
                .param("username", "lorraine")
                .param("password", "athlone"))
                .andDo(print())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAdminCredentials() throws Exception {
        mvc.perform(post("/login")
                .param("username", "admin")
                .param("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/home"));
    }

    @Test
    void testWrongUserCredentials() throws Exception {
        mvc.perform(post("/login")
                .param("username", "xamre")
                .param("password", "xyz"))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }
}
