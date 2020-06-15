package com.laqr.NewspaperDeliverySystem.controller.admin.customer;

import com.laqr.NewspaperDeliverySystem.services.CustomerService;
import com.laqr.NewspaperDeliverySystem.services.DeliveryPersonService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class DeleteCustomerController {

    final CustomerService customerService;
    final UserService userService;
    final UserUtils userUtils;

    public DeleteCustomerController(CustomerService customerService, UserService userService, UserUtils userUtils) {
        this.customerService = customerService;
        this.userService = userService;
        this.userUtils = userUtils;
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("customer-id") Integer customerId
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            customerService.deleteCustomer(customerId);
            redirectAttributes.addFlashAttribute("success", "Successfully Deleted Customer");
            return "redirect:/admin/view-customers";
        } else
            return "redirect:/";
    }
}
