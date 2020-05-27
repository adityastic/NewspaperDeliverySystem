package com.laqr.NewspaperDeliverySystem.controller.admin.customer;

import com.laqr.NewspaperDeliverySystem.services.CustomerService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ViewCustomerController {

    final CustomerService customerService;
    final UserService userService;
    final UserUtils userUtils;

    public ViewCustomerController(CustomerService customerService, UserService userService, UserUtils userUtils) {
        this.customerService = customerService;
        this.userService = userService;
        this.userUtils = userUtils;
    }

    @GetMapping("/view-customers")
    public String viewCustomers(
            ModelMap model,
            HttpSession session
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("customerList", customerService.getAllCustomers());
            return "admin/customer/view";
        } else
            return "redirect:/";
    }
}
