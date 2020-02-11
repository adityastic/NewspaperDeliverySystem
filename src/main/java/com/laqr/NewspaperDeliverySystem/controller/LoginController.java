package com.laqr.NewspaperDeliverySystem.controller;

import com.laqr.NewspaperDeliverySystem.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ModelAndView loginHandler(
            ModelMap mp,
            @RequestParam("username")String username,
            @RequestParam("password")String password
    ){
        if (loginService.checkUser(username, password)){
            return new ModelAndView("forward:/home", "name", username);
        } else {
            return new ModelAndView("forward:/", "error", "username and password is incorrect");
        }
    }
}
