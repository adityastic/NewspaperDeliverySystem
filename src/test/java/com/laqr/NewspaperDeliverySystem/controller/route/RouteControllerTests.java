package com.laqr.NewspaperDeliverySystem.controller.route;

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
public class RouteControllerTests {
    @Autowired
    MockMvc mvc;

    @Test
    void addRouteGetTest() throws Exception {
        mvc.perform(get("/admin/add-route")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(view().name("admin/route/add"));
    }

    @Test
    void addRoutePostTest() throws Exception {
        mvc.perform(post("/admin/add-route")
                .param("route-name", "Dublin")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/view-route"))
                .andExpect(flash().attribute("success", "Successfully Added Route"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/routes_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/routes_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editRouteGetTest() throws Exception {
        mvc.perform(get("/admin/edit-route/1")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(view().name("admin/route/edit"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/routes_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/routes_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editRoutePostTest() throws Exception {
        mvc.perform(post("/admin/edit-route")
                .param("route-id","1")
                .param("route-name", "Dublin")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/view-route"))
                .andExpect(flash().attribute("success", "Successfully Edited Route "));
    }
}
