package com.laqr.NewspaperDeliverySystem.controller.admin.customer;

import com.laqr.NewspaperDeliverySystem.services.CustomerService;
import com.laqr.NewspaperDeliverySystem.services.ProductService;
import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.CustomerUtils;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class EditCustomerController {

    final CustomerService customerService;
    final UserService userService;
    final RouteService routeService;
    final ProductService productService;
    final UserUtils userUtils;
    final CustomerUtils customerUtils;

    public EditCustomerController(CustomerService customerService, UserService userService, RouteService routeService, ProductService productService, UserUtils userUtils, CustomerUtils customerUtils) {
        this.customerService = customerService;
        this.userService = userService;
        this.routeService = routeService;
        this.productService = productService;
        this.userUtils = userUtils;
        this.customerUtils = customerUtils;
    }

    @GetMapping("/edit-customer/{id}")
    public String editCustomerGet(
            ModelMap model,
            HttpSession session,
            @PathVariable("id") Integer customerId
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("customer", customerService.getCustomerById(customerId));
            model.addAttribute("allRoutes", routeService.getAllRoutes());
            model.addAttribute("allProducts", productService.getAllProducts());
            return "admin/customer/edit";
        } else
            return "redirect:/";
    }

    @PostMapping("/edit-customer")
    public String editCustomerPost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("customer-id") Integer customerId,
            @RequestParam("full-name") String fullName,
            @RequestParam("phone-no") String phoneNo,
            @RequestParam("address") String address,
            @RequestParam(value = "subscription[]", required = false) List<Integer> subscriptions,
            @RequestParam(value = "routeSelected", required = false) Integer routeSelected,
            @RequestParam(value = "holidays[]", required = false) List<String> holidays
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            if (customerUtils.checkEditedCustomer(customerId, fullName, phoneNo, address, subscriptions, routeSelected, holidays, customerService, redirectAttributes)) {
                customerService.editCustomer(customerId, fullName, phoneNo, address, subscriptions, routeSelected, holidays);
                redirectAttributes.addFlashAttribute("success", "Successfully Edited Customer");
                return "redirect:/admin/view-customers";
            } else {
                return "redirect:/admin/edit-customer/" + customerId;
            }
        } else
            return "redirect:/";
    }
}
