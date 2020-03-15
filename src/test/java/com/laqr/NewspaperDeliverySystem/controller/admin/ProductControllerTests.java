package com.laqr.NewspaperDeliverySystem.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
