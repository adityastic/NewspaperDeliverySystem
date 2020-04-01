package com.laqr.NewspaperDeliverySystem.controller.admin.delivery_person;

import com.laqr.NewspaperDeliverySystem.services.DeliveryPersonService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ViewDPController {

    final DeliveryPersonService productService;
    final UserService userService;
    final UserUtils userUtils;

    public ViewDPController(DeliveryPersonService productService, UserService userService, UserUtils userUtils) {
        this.productService = productService;
        this.userService = userService;
        this.userUtils = userUtils;
    }

    @GetMapping("/view-delivery-persons")
    public String viewDeliveryPersons(
            ModelMap model,
            HttpSession session
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("dpList", productService.getAllDeliveryPersons());
            return "admin/delivery_person/view";
        } else
            return "redirect:/";
    }
}
