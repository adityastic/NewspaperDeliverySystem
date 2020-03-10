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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
            model.addAttribute("product", productService.getProductByID(productID));
            model.addAttribute("productFrequencies", ProductFrequency.values());
            model.addAttribute("productTypes", ProductType.values());
            return "admin/product/edit";
        } else
            return "redirect:/";
    }
}
