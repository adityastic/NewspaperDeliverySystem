package com.laqr.NewspaperDeliverySystem.util;

import com.laqr.NewspaperDeliverySystem.services.DeliveryPersonService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Pattern;

@Component
public class UserRegistrationUtils {

    public boolean checkUserName(String username, DeliveryPersonService deliveryPersonService, RedirectAttributes redirectAttributes) {
        if (deliveryPersonService.checkUsername(username)) {
            redirectAttributes.addFlashAttribute("error", "Username already exists");
            return false;
        }
        if (username == null || username.trim().equals("")) {
            redirectAttributes.addFlashAttribute("error", "No Username Entered");
            return false;
        }
        if (!validLength(username)) {
            redirectAttributes.addFlashAttribute("error", "Username should be from length 4 - 15");
            return false;
        }
        if (validUsername(username)) {
            redirectAttributes.addFlashAttribute("error", "Username should not contain any special characters");
            return false;
        }
        return true;
    }

    public boolean checkPassword(String password, RedirectAttributes redirectAttributes) {
        if (password == null || password.trim().equals("")) {
            redirectAttributes.addFlashAttribute("error", "No Password Entered");
            return false;
        }
        if (!validPasswordLength(password)) {
            redirectAttributes.addFlashAttribute("error", "Password should be at least 4");
            return false;
        }
        return true;
    }

    public boolean checkPhoneNo(String phoneNo, RedirectAttributes redirectAttributes) {
        if (phoneNo == null || phoneNo.trim().equals("")) {
            redirectAttributes.addFlashAttribute("error", "No Phone Number Entered");
            return false;
        }
        if (phoneNo.length() != 10) {
            redirectAttributes.addFlashAttribute("error", "Phone Number should be of 10 numbers");
            return false;
        }
        return true;
    }

    public boolean checkFullName(String fullName, RedirectAttributes redirectAttributes) {
        if (fullName == null || fullName.trim().equals("")) {
            redirectAttributes.addFlashAttribute("error", "No Full Name Entered");
            return false;
        }
        return true;
    }

    private boolean validUsername(String username) {
        return Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]").matcher(username).find();
    }

    private boolean validPasswordLength(String password) {
        return password.length() > 3;
    }

    private boolean validLength(String username) {
        return username.length() > 3 && username.length() < 16;
    }
}
