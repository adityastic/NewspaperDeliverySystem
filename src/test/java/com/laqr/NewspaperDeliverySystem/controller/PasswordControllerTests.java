package com.laqr.NewspaperDeliverySystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testChangePasswordSuccess() throws Exception {
        mvc.perform(post("/forgot-password")
                .param("username", "lorraine")
                .param("password", "lorraine"))
                .andDo(print())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attributeExists("success"));
    }

    @Test
    void testChangePasswordError() throws Exception {
        mvc.perform(post("/forgot-password")
                .param("username", "asd")
                .param("password", "asdas"))
                .andExpect(redirectedUrl("/forgot-password"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    void testChangePassword() throws Exception {
        mvc.perform(get("/forgot-password"))
                .andExpect(view().name("forgot"));
    }
}
