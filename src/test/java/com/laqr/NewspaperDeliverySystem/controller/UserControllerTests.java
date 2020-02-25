package com.laqr.NewspaperDeliverySystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUserDashboardWithCorrectCreds() throws Exception {
        mvc.perform(get("/home")
                .sessionAttr("username", "lorraine")
                .sessionAttr("password", "athlone"))
                .andExpect(view().name("user/home"));
    }

    @Test
    void testUserDashboardWithWrongCreds() throws Exception {
        mvc.perform(get("/home")
                .sessionAttr("username", "asd")
                .sessionAttr("password", "asdas"))
                .andExpect(redirectedUrl("/"));
    }
}
