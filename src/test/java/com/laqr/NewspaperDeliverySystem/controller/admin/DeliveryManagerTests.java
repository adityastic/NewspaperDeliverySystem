package com.laqr.NewspaperDeliverySystem.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
public class DeliveryManagerTests {

    @Autowired
    MockMvc mvc;

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void usernameAlreadyExists() throws Exception {
        mvc.perform(post("/admin/register-delivery-persons")
                .param("username", "lorraine")
                .param("password", "athlone")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/register-delivery-persons"))
                .andExpect(flash().attribute("error", "Username already exists"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void usernameNotEntered() throws Exception {
        mvc.perform(post("/admin/register-delivery-persons")
                .param("username", "")
                .param("password", "athlone")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/register-delivery-persons"))
                .andExpect(flash().attribute("error", "No Username Entered"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void usernameLengthLess4() throws Exception {
        mvc.perform(post("/admin/register-delivery-persons")
                .param("username", "lor")
                .param("password", "athlone")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/register-delivery-persons"))
                .andExpect(flash().attribute("error", "Username should be from length 4 - 15"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void usernameLengthGreat15() throws Exception {
        mvc.perform(post("/admin/register-delivery-persons")
                .param("username", "lorraineasdasdasdasd")
                .param("password", "athlone")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/register-delivery-persons"))
                .andExpect(flash().attribute("error", "Username should be from length 4 - 15"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void usernameSpecialChar() throws Exception {
        mvc.perform(post("/admin/register-delivery-persons")
                .param("username", "lorrai@as")
                .param("password", "athlone")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/register-delivery-persons"))
                .andExpect(flash().attribute("error", "Username should not contain any special characters"));
    }
}
