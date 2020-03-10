package com.laqr.NewspaperDeliverySystem.controller.admin.product;

import com.laqr.NewspaperDeliverySystem.services.ProductService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")

public class ViewProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    UserUtils userUtils;

    @GetMapping("/view-products")
    public String viewProducts(
            ModelMap model,
            HttpSession session
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("productList", productService.getAllProducts());
            return "admin/product/view";
        } else
            return "redirect:/";
    }
}
