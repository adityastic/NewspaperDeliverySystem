package com.laqr.NewspaperDeliverySystem.controller;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String firstPage(
            ModelMap model,
            HttpSession session
    ) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        User currentUser = userService.getUser(username, password);
        if (currentUser != null) {
            model.addAttribute(currentUser);
            return "user/home";
        } else {
            return "redirect:/";
        }
    }
}
