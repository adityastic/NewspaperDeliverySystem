package com.laqr.NewspaperDeliverySystem.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Component
public class CustomerUtils {

    public boolean checkCustomer(String fullName, String phoneNo, String address, List<Integer> subscriptions, Integer routeSelected, RedirectAttributes redirectAttributes) {
        if (fullName.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No Full Name Entered");
            return false;
        }

        if (phoneNo.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No Phone Number Entered");
            return false;
        }

        if (phoneNo.length() != 10) {
            redirectAttributes.addFlashAttribute("error", "Phone Number should be of 10 numbers");
            return false;
        }

        if (address.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No Address Entered");
            return false;
        }

        if (subscriptions.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Select at least one subscription");
            return false;
        }

        if (routeSelected == null) {
            redirectAttributes.addFlashAttribute("error", "Select one route");
            return false;
        }

        return true;
    }
}
