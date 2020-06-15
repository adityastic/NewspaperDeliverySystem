package com.laqr.NewspaperDeliverySystem.controller.admin.customer;

import com.laqr.NewspaperDeliverySystem.services.CustomerService;
import com.laqr.NewspaperDeliverySystem.services.ProductService;
import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.CustomerUtils;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AddCustomerController {

    final CustomerService customerService;
    final UserService userService;
    final RouteService routeService;
    final ProductService productService;
    final UserUtils userUtils;
    final CustomerUtils customerUtils;

    public AddCustomerController(CustomerService customerService, UserService userService, RouteService routeService, ProductService productService, UserUtils userUtils, CustomerUtils customerUtils) {
        this.customerService = customerService;
        this.userService = userService;
        this.routeService = routeService;
        this.productService = productService;
        this.userUtils = userUtils;
        this.customerUtils = customerUtils;
    }

    @GetMapping("/add-customer")
    public String addCustomerGet(
            ModelMap model,
            HttpSession session
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("allRoutes", routeService.getAllRoutes());
            model.addAttribute("allProducts", productService.getAllProducts());
            return "admin/customer/add";
        } else
            return "redirect:/";
    }

    @PostMapping("/add-customer")
    public String addCustomerPost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("full-name") String fullName,
            @RequestParam("phone-no") String phoneNo,
            @RequestParam("address") String address,
            @RequestParam(value = "subscription[]", required = false) List<Integer> subscriptions,
            @RequestParam(value = "routeSelected", required = false) Integer routeSelected
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            if (customerUtils.checkCustomer(fullName, phoneNo, address, subscriptions, routeSelected, redirectAttributes)) {
                customerService.addCustomer(fullName, phoneNo, address, subscriptions, routeSelected);
                redirectAttributes.addFlashAttribute("success", "Added Customer!");
                return "redirect:/admin/view-customers";
            } else {
                redirectAttributes.addFlashAttribute("fullNameStored", fullName);
                redirectAttributes.addFlashAttribute("addressStored", address);
                redirectAttributes.addFlashAttribute("phoneStored", phoneNo);
                redirectAttributes.addFlashAttribute("subscriptionsStored", subscriptions);
                redirectAttributes.addFlashAttribute("routeStored", routeSelected);
                return "redirect:/admin/add-customer";
            }
        } else
            return "redirect:/";
    }
}
