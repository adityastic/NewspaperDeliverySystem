package com.laqr.NewspaperDeliverySystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class UserController {

    @PostMapping( "/home")
    public String firstPage(ModelMap model) {
        return "user/home";
    }
}
