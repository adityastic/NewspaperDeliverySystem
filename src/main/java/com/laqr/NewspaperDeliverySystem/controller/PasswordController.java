package com.laqr.NewspaperDeliverySystem.controller;

import com.laqr.NewspaperDeliverySystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PasswordController {

    @Autowired
    UserService userService;

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot";
    }

    @PostMapping("/forgot-password")
    public String changePassword(
            RedirectAttributes redirectAttributes,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        if(userService.changePassword(username,password)){
            redirectAttributes.addFlashAttribute("success", "Password changed, Welcome to Login");
            return "redirect:/";
        }else{
            redirectAttributes.addFlashAttribute("error", "Username not found");
            return "redirect:/forgotPassword";
        }

    }

}
