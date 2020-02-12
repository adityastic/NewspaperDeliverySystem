package com.laqr.NewspaperDeliverySystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {

    @GetMapping( "/home")
    public String firstPage(ModelMap model) {
        return "user/home";
    }
}
