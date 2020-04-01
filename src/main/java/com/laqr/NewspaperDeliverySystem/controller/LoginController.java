package com.laqr.NewspaperDeliverySystem.controller;

import com.laqr.NewspaperDeliverySystem.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(value = "/")
    public ModelAndView firstPage(
            HttpSession session
    ) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (username != null &&
                password != null) {

            if (loginService.checkAdmin(username, password)) {
                session.setAttribute("username", username);
                session.setAttribute("password", username);
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
        if (loginService.checkAdmin(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("password", username);
            return new ModelAndView("redirect:/admin/home");
        } else if (loginService.checkUser(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            return new ModelAndView("redirect:/home");
        } else {
            redirectAttributes.addFlashAttribute("error", "Username and Password is incorrect!");
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
}
