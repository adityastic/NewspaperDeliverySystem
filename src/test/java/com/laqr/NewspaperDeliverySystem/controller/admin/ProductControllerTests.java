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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {
    @Autowired
    MockMvc mvc;

    @Test
    void viewProductGetTest() throws Exception {
        mvc.perform(get("/admin/view-products")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(view().name("admin/product/view"));
    }

    @Test
    void viewProductGetTestWithLoginFail() throws Exception {
        mvc.perform(get("/admin/view-products")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void addProductGetTest() throws Exception {
        mvc.perform(get("/admin/add-product")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(view().name("admin/product/add"));
    }

    @Test
    void addProductGetTestWithoutLogin() throws Exception {
        mvc.perform(get("/admin/add-product")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }


    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editProductGetTest() throws Exception {
        mvc.perform(get("/admin/edit-product/60")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(view().name("admin/product/edit"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editProductGetTestWithWrongUser() throws Exception {
        mvc.perform(get("/admin/edit-product/60")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editProductPostNamePresentTest() throws Exception {
        mvc.perform(post("/admin/edit-product")
                .param("product-id", "60")
                .param("product-name", "Athlone Weekly")
                .param("product-type", "NEWSPAPER")
                .param("product-frequency", "WEEKLY")
                .param("product-dow", "7")
                .param("product-sellingCost", "1.2")
                .param("product-buyingCost", "1.0")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/edit-product/60"))
                .andExpect(flash().attribute("error","Product name already exists"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editProductPostNameEmptyTest() throws Exception {
        mvc.perform(post("/admin/edit-product")
                .param("product-id", "60")
                .param("product-name", "")
                .param("product-type", "NEWSPAPER")
                .param("product-frequency", "WEEKLY")
                .param("product-dow", "7")
                .param("product-sellingCost", "1.2")
                .param("product-buyingCost", "1.0")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/edit-product/60"))
                .andExpect(flash().attribute("error","No Product name is Entered"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editProductCorrectTest() throws Exception {
        mvc.perform(post("/admin/edit-product")
                .param("product-id", "60")
                .param("product-name", "Westmeath Daily")
                .param("product-type", "NEWSPAPER")
                .param("product-frequency", "WEEKLY")
                .param("product-dow", "7")
                .param("product-sellingCost", "1.2")
                .param("product-buyingCost", "1.0")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/view-products"))
                .andExpect(flash().attribute("success","Successfully Edited Product "));
    }

    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void editProductWrongUserTest() throws Exception {
        mvc.perform(post("/admin/edit-product")
                .param("product-id", "60")
                .param("product-name", "Westmeath Daily")
                .param("product-type", "NEWSPAPER")
                .param("product-frequency", "WEEKLY")
                .param("product-dow", "7")
                .param("product-sellingCost", "1.2")
                .param("product-buyingCost", "1.0")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }
    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deleteProductPostTest() throws Exception {
        mvc.perform(post("/admin/delete-product")
                .param("product-id", "60")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/view-products"))
                .andExpect(flash().attribute("success", "Successfully Deleted Product"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deleteProductPostFailedLoginTest() throws Exception {
        mvc.perform(post("/admin/delete-product")
                .param("product-id", "60")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addProductPostTestLoginFail() throws Exception {
        mvc.perform(post("/admin/add-product")
                .param("product-name", "Westmeath Daily")
                .param("product-type", "NEWSPAPER")
                .param("product-frequency", "WEEKLY")
                .param("product-dow", "7")
                .param("product-sellingCost", "1.2")
                .param("product-buyingCost", "1.0")
                .sessionAttr("username", "admin")
                .sessionAttr("password", ""))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addProductPostTest() throws Exception {
        mvc.perform(post("/admin/add-product")
                .param("product-name", "Westmeath Daily")
                .param("product-type", "NEWSPAPER")
                .param("product-frequency", "WEEKLY")
                .param("product-dow", "7")
                .param("product-sellingCost", "1.2")
                .param("product-buyingCost", "1.0")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/view-products"))
                .andExpect(flash().attribute("success","Successfully Added Product"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addProductPostAlreadyExistsTest() throws Exception {
        mvc.perform(post("/admin/add-product")
                .param("product-name", "Athlone Daily")
                .param("product-type", "NEWSPAPER")
                .param("product-frequency", "WEEKLY")
                .param("product-dow", "7")
                .param("product-sellingCost", "1.2")
                .param("product-buyingCost", "1.0")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-product"))
                .andExpect(flash().attribute("error","Product name already exists"));
    }

    @Test
    @Sql(scripts = "/scripts/controller/products_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/controller/products_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addProductPostNotEnteredTest() throws Exception {
        mvc.perform(post("/admin/add-product")
                .param("product-name", "")
                .param("product-type", "NEWSPAPER")
                .param("product-frequency", "WEEKLY")
                .param("product-dow", "7")
                .param("product-sellingCost", "1.2")
                .param("product-buyingCost", "1.0")
                .sessionAttr("username", "admin")
                .sessionAttr("password", "admin"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/add-product"))
                .andExpect(flash().attribute("error","No Product name is Entered"));
    }
}
