package com.laqr.NewspaperDeliverySystem.controller;

import com.laqr.NewspaperDeliverySystem.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/")
    public ModelAndView firstPage(
            HttpSession session
    ) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (username != null &&
                password != null) {

            if (username.equals("admin") && password.equals("admin")) {
                session.setAttribute("username", "admin");
                session.setAttribute("password", "admin");
                return new ModelAndView("redirect:/admin/home");
            } else if (loginService.checkUser(username, password)) {
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                return new ModelAndView("redirect:/home");
            } else {
                session.invalidate();
                return new ModelAndView("redirect:/");
            }
        }
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView loginHandler(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        if (username.equals("admin") && password.equals("admin")) {
            session.setAttribute("username", "admin");
            session.setAttribute("password", "admin");
            return new ModelAndView("redirect:/admin/home");
        } else if (loginService.checkUser(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            return new ModelAndView("redirect:/home");
        } else {
            redirectAttributes.addFlashAttribute("error", "username and password is incorrect");
            return new ModelAndView("redirect:/");
        }
    }


    @GetMapping(value = "/logout")
    public String logout(
            HttpSession session
    ) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping(value = "/forgotPassword")
    public String forgotPassword()
    {
        return "forgot";
    }



}
