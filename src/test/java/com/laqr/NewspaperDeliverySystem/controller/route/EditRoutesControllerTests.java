package com.laqr.NewspaperDeliverySystem.controller.route;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class EditRoutesControllerTests {
    @Autowired
    MockMvc mvc;

    @Test
    @Sql(scripts = "/scripts/controller/route_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/route_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void routeAlreadyExists() throws Exception {
        mvc.perform(post("/admin/edit-route")
                .param("route", "Athlone")
                 .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/edit-route"))
                .andExpect(flash().attribute("error", "Route already exists"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/route_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/route_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void routeValid() throws Exception {
        mvc.perform(post("/admin/edit-route")
                .param("route", "Dublin")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/view-route"))
                .andExpect(flash().attribute("error", "Route already exists"));
    }
}
