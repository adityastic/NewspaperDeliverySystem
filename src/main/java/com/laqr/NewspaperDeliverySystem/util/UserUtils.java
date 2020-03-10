package com.laqr.NewspaperDeliverySystem.util;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

@Component
public class UserUtils {
    public boolean isValidAdmin(HttpSession session, UserService userService, ModelMap model) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(username, password);
        if (currentUser != null) {
            if (model != null)
                model.addAttribute("user", currentUser);
            return true;
        } else {
            return false;
        }
    }
}
