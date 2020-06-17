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
public class CustomerControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    void viewCustomersGetTest() throws Exception {
        mvc.perform(get("/admin/view-customers")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(view().name("admin/customer/view"));
    }

    @Test
    void viewCustomersGetFailedTest() throws Exception {
        mvc.perform(get("/admin/view-customers")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void addCustomerGetTest() throws Exception {
        mvc.perform(get("/admin/add-customer")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andExpect(view().name("admin/customer/add"));
    }

    @Test
    void addCustomerGetFailedTest() throws Exception {
        mvc.perform(get("/admin/add-customer")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/customer_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/customer_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addCustomerPostIncorrectLoginTest() throws Exception {
        mvc.perform(post("/admin/add-customer")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "1234567890")
                .param("address", "ASDASD")
                .param("subscription", "10000,10001")
                .param("routeSelected", "10000")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/customer_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/customer_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addCustomerPostCorrectTest() throws Exception {
        mvc.perform(post("/admin/add-customer")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "1234567890")
                .param("address", "ASDASD")
                .param("subscription[]", "10000,10001")
                .param("routeSelected", "10000")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/view-customers"))
                .andExpect(flash().attribute("success", "Added Customer!"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/customer_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/customer_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addCustomerPostIncorrectPhoneLengthTest() throws Exception {
        mvc.perform(post("/admin/add-customer")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "123456789")
                .param("address", "ASDASD")
                .param("subscription[]", "10000,10001")
                .param("routeSelected", "10000")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-customer"))
                .andExpect(flash().attribute("error", "Phone Number should be of 10 numbers"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/customer_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/customer_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addCustomerPostIncorrectPhoneTest() throws Exception {
        mvc.perform(post("/admin/add-customer")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "")
                .param("address", "ASDASD")
                .param("subscription[]", "10000,10001")
                .param("routeSelected", "10000")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-customer"))
                .andExpect(flash().attribute("error", "No Phone Number Entered"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/customer_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/customer_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addCustomerPostIncorrectFullNameTest() throws Exception {
        mvc.perform(post("/admin/add-customer")
                .param("full-name", "")
                .param("phone-no", "1234567890")
                .param("address", "ASDASD")
                .param("subscription[]", "10000,10001")
                .param("routeSelected", "10000")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-customer"))
                .andExpect(flash().attribute("error", "No Full Name Entered"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/customer_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/customer_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addCustomerPostIncorrectAddressTest() throws Exception {
        mvc.perform(post("/admin/add-customer")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "1234567890")
                .param("address", "")
                .param("subscription[]", "10000,10001")
                .param("routeSelected", "10000")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-customer"))
                .andExpect(flash().attribute("error", "No Address Entered"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/customer_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/customer_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addCustomerPostIncorrectSubscriptionTest() throws Exception {
        mvc.perform(post("/admin/add-customer")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "1234567890")
                .param("address", "ASDASD")
                .param("routeSelected", "10000")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-customer"))
                .andExpect(flash().attribute("error", "Select at least one subscription"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/customer_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/customer_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addCustomerPostIncorrectRouteTest() throws Exception {
        mvc.perform(post("/admin/add-customer")
                .param("full-name", "Aditya Gupta")
                .param("phone-no", "1234567890")
                .param("address", "ASDASD")
                .param("subscription[]", "10000,10001")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-customer"))
                .andExpect(flash().attribute("error", "Select one route"));
    }
}
