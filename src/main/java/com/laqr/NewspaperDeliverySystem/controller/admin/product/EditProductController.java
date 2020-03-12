package com.laqr.NewspaperDeliverySystem.controller.admin.product;

import com.laqr.NewspaperDeliverySystem.model.ProductFrequency;
import com.laqr.NewspaperDeliverySystem.model.ProductType;
import com.laqr.NewspaperDeliverySystem.services.ProductService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.ProductUtils;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class EditProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    ProductUtils productUtils;

    @Autowired
    UserUtils userUtils;

    @GetMapping("/edit-product/{id}")
    public String editProductGet(
            ModelMap model,
            HttpSession session,
            @PathVariable("id") Integer productID
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("product", productService.getProductById(productID));
            model.addAttribute("productFrequencies", ProductFrequency.values());
            model.addAttribute("productTypes", ProductType.values());
            return "admin/product/edit";
        } else
            return "redirect:/";
    }

    @PostMapping("/edit-product")
    public String editProductPost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("product-id") Integer productID,
            @RequestParam("product-name") String productName,
            @RequestParam("product-type") ProductType productType,
            @RequestParam("product-frequency") ProductFrequency frequency,
            @RequestParam(value = "product-dow", required = false) Integer dayOfWeek,
            @RequestParam("product-sellingCost") Double sellingCost,
            @RequestParam("product-buyingCost") Double buyingCost
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            if (productUtils.checkEditProductName(productID, productName, productService, redirectAttributes)) {
                productService.editProduct(productID, productName, productType, frequency, dayOfWeek, sellingCost, buyingCost);
                redirectAttributes.addFlashAttribute("success", "Successfully Edited Product ");
                return "redirect:/admin/view-products";
            } else {
                return "redirect:/admin/edit-product/" + productID;
            }
        } else
            return "redirect:/";
    }

}
