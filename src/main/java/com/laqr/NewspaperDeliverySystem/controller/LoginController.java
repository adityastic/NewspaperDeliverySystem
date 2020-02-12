package com.laqr.NewspaperDeliverySystem.controller;

import com.laqr.NewspaperDeliverySystem.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ModelAndView loginHandler(
            RedirectAttributes redirectAttributes,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        if (username.equals("admin") && password.equals("admin")) {
            return new ModelAndView("redirect:/admin/home");
        } else if (loginService.checkUser(username, password)) {
            redirectAttributes.addFlashAttribute("name", username);
            return new ModelAndView("redirect:/home");
        } else {
            redirectAttributes.addFlashAttribute("error", "username and password is incorrect");
            return new ModelAndView("redirect:/");
        }
    }
}
