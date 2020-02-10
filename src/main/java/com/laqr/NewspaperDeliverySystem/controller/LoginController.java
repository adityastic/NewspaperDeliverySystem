package com.laqr.NewspaperDeliverySystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @PostMapping("/login")
    public ModelAndView loginHandler(
            ModelMap mp,
            @RequestParam("username")String username,
            @RequestParam("password")String password
    ){
        if (username.equals("admin")&& password.equals("admin")){
            return new ModelAndView("forward/admin/home");
        }else if (userService)
    }
}
