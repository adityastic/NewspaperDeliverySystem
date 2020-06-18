package com.laqr.NewspaperDeliverySystem.controller.admin.product;

import com.laqr.NewspaperDeliverySystem.model.ProductFrequency;
import com.laqr.NewspaperDeliverySystem.model.ProductType;
import com.laqr.NewspaperDeliverySystem.services.ProductService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.ProductUtils;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
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

    final ProductService productService;
    final UserService userService;
    final ProductUtils productUtils;
    final UserUtils userUtils;

    public AddProductController(ProductService productService, UserService userService, ProductUtils productUtils, UserUtils userUtils) {
        this.productService = productService;
        this.userService = userService;
        this.productUtils = productUtils;
        this.userUtils = userUtils;
    }

    @GetMapping("/add-product")
    public String addProductGet(
            ModelMap model,
            HttpSession session
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("productFrequencies", ProductFrequency.values());
            model.addAttribute("productTypes", ProductType.values());
            return "admin/product/add";
        } else
            return "redirect:/";
    }

    @PostMapping("/add-product")
    public String addProductPost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("product-name") String productName,
            @RequestParam("product-type") ProductType productType,
            @RequestParam("product-frequency") ProductFrequency frequency,
            @RequestParam(value = "product-dow", required = false) Integer dayOfWeek,
            @RequestParam("product-sellingCost") Double sellingCost,
            @RequestParam("product-buyingCost") Double buyingCost
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            if (productUtils.checkProductName(productName, productService, redirectAttributes)) {
                productService.addProduct(productName, productType, frequency, dayOfWeek, sellingCost, buyingCost);
                redirectAttributes.addFlashAttribute("success", "Successfully Added Product");
                return "redirect:/admin/view-products";
            } else {
                redirectAttributes.addFlashAttribute("nameStored", productName);
                redirectAttributes.addFlashAttribute("typeStored", productType);
                redirectAttributes.addFlashAttribute("frequencyStored", frequency);
                redirectAttributes.addFlashAttribute("sCStored", sellingCost);
                redirectAttributes.addFlashAttribute("bCStored", buyingCost);
                redirectAttributes.addFlashAttribute("dowStored", dayOfWeek);
                return "redirect:/admin/add-product";
            }
        } else
            return "redirect:/";
    }
}
