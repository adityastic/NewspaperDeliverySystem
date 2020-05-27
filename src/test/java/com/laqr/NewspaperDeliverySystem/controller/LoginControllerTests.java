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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void homePageAdmin() throws Exception {
        mvc.perform(get("/")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/home"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void homePageUser() throws Exception {
        mvc.perform(get("/")
                .sessionAttr("username", "lorraine")
                .sessionAttr("password", "athlone"))
                .andDo(print())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void homePageFailedLogin() throws Exception {
        mvc.perform(get("/")
                .sessionAttr("username", "lorraine")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void homePage() throws Exception {
        mvc.perform(get("/"))
                .andDo(print())
                .andExpect(view().name("login"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void homePageWithUsername() throws Exception {
        mvc.perform(get("/")
                .sessionAttr("username", "lorraine"))
                .andDo(print())
                .andExpect(view().name("login"));
    }

    @Test
    void logout() throws Exception {
        mvc.perform(get("/logout"))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }
}
