package com.laqr.NewspaperDeliverySystem.controller.admin.product;

import com.laqr.NewspaperDeliverySystem.model.ProductType;
import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.services.ProductService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AddProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    ProductUtils productUtils;

    @GetMapping("/add-product")
    public String addProductGet(
            ModelMap model,
            HttpSession session
    ) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(username, password);
        if (currentUser != null) {
            model.addAttribute(currentUser);
            return "admin/product/add";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add-product")
    public String addProductPost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("product-name") String productName,
            @RequestParam("product-type") ProductType productType,
            @RequestParam("product-frequency") String frequency,
            @RequestParam("product-dow") Integer dayOfWeek,
            @RequestParam("product-sellingCost") Double sellingCost,
            @RequestParam("product-buyingCost") Double buyingCost

    ) {

        String currentUsername = (String) session.getAttribute("username");
        String currentPassword = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(currentUsername, currentPassword);
        if (currentUser == null)
            return "redirect:/";

        if(productUtils.checkProductName(productName, productService, redirectAttributes)){
            productService.addProduct(productName, productType, frequency, dayOfWeek, sellingCost, buyingCost);
            redirectAttributes.addFlashAttribute("success","Successfully Added Product");
            return "redirect:/admin/view-products";
        }else{
            redirectAttributes.addFlashAttribute("productStored", productName);
            return "redirect:/admin/add-product";
        }
    }
}
