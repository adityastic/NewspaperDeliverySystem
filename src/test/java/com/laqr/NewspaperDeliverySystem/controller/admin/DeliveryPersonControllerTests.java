package com.laqr.NewspaperDeliverySystem.controller.admin;

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
public class DeliveryPersonControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void usernameAlreadyExists() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "lorraine")
                .param("password", "athlone")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-delivery-persons"))
                .andExpect(flash().attribute("error", "Username already exists"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void usernameNotEntered() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "")
                .param("password", "athlone")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-delivery-persons"))
                .andExpect(flash().attribute("error", "No Username Entered"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void usernameLengthLess4() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "lor")
                .param("password", "athlone")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-delivery-persons"))
                .andExpect(flash().attribute("error", "Username should be from length 4 - 15"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void usernameLengthGreat15() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "lorraineasdasdasdasd")
                .param("password", "athlone")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-delivery-persons"))
                .andExpect(flash().attribute("error", "Username should be from length 4 - 15"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void usernameSpecialChar() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "lorrai@as")
                .param("password", "athlone")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-delivery-persons"))
                .andExpect(flash().attribute("error", "Username should not contain any special characters"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void passwordNotEntered() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "lorraines")
                .param("password", "")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-delivery-persons"))
                .andExpect(flash().attribute("error", "No Password Entered"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void passwordLength() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "lorraines")
                .param("password", "123")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "0123456789")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-delivery-persons"))
                .andExpect(flash().attribute("error", "Password should be at least 4"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void phoneNoNotEntered() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "lorraines")
                .param("password", "1234")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-delivery-persons"))
                .andExpect(flash().attribute("error", "No Phone Number Entered"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void phoneNoLength() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "lorrainess")
                .param("password", "1234")
                .param("full-name", "Lorraine Armitage")
                .param("phone-no", "12345")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-delivery-persons"))
                .andExpect(flash().attribute("error", "Phone Number should be of 10 numbers"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void fullNameNotEntered() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "lorraines")
                .param("password", "1234")
                .param("full-name", "")
                .param("phone-no", "1234567890")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-delivery-persons"))
                .andExpect(flash().attribute("error", "No Full Name Entered"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void detailsCorrect() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "adityagupta")
                .param("password", "123456")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "1234567890")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/view-delivery-persons"))
                .andExpect(flash().attribute("success", "Added Delivery Person"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void wrongUsernamePassword() throws Exception {
        mvc.perform(post("/admin/add-delivery-persons")
                .param("username", "adityagupta")
                .param("password", "123456")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "1234567890")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getTest() throws Exception {
        mvc.perform(get("/admin/add-delivery-persons")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andExpect(view().name("admin/delivery_person/add"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getTestWrongAdmin() throws Exception {
        mvc.perform(get("/admin/add-delivery-persons")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andExpect(redirectedUrl("/"));
    }


    @Test
    void viewDPGetTest() throws Exception {
        mvc.perform(get("/admin/view-delivery-persons")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(view().name("admin/delivery_person/view"));
    }

    @Test
    void viewDPGetTestWithLoginFail() throws Exception {
        mvc.perform(get("/admin/view-delivery-persons")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }


    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deleteDPPostTest() throws Exception {
        mvc.perform(post("/admin/delete-delivery-persons")
                .param("dp-id", "100")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/view-delivery-persons"))
                .andExpect(flash().attribute("success", "Successfully Deleted Delivery Person"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deleteDPPostTestLoginFail() throws Exception {
        mvc.perform(post("/admin/delete-delivery-persons")
                .param("dp-id", "100")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }


    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editDPGetTest() throws Exception {
        mvc.perform(get("/admin/edit-delivery-persons/100")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(view().name("admin/delivery_person/edit"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editDPGetTestWithWrongUser() throws Exception {
        mvc.perform(get("/admin/edit-delivery-persons/100")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editRoutePostTest() throws Exception {
        mvc.perform(post("/admin/edit-delivery-persons")
                .param("dp-id", "100")
                .param("username", "Rupal")
                .param("password", "123456")
                .param("full-name", "Rupal Singh")
                .param("phone-no", "1234567890")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/view-delivery-persons"))
                .andExpect(flash().attribute("success", "Successfully Edited Delivery Person "));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editRoutePostTestWithWrongLogin() throws Exception {
        mvc.perform(post("/admin/edit-delivery-persons")
                .param("dp-id", "100")
                .param("username", "Rupal")
                .param("password", "123456")
                .param("full-name", "Rupal Singh")
                .param("phone-no", "1234567890")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editRoutePostTestUsernameExists() throws Exception {
        mvc.perform(post("/admin/edit-delivery-persons")
                .param("dp-id", "100")
                .param("username", "aditya")
                .param("password", "123456")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "1234567890")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/edit-delivery-persons/100"))
                .andExpect(flash().attribute("error", "Username name already exists"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/users_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/users_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editRoutePostTestUsernameNotEntered() throws Exception {
        mvc.perform(post("/admin/edit-delivery-persons")
                .param("dp-id", "100")
                .param("username", "")
                .param("password", "123456")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "1234567890")
                .param("routeSelected", "10")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/edit-delivery-persons/100"))
                .andExpect(flash().attribute("error", "No Username is Entered"));
    }
}
