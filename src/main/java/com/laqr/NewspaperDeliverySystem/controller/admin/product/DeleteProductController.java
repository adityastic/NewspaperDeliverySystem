package com.laqr.NewspaperDeliverySystem.controller.admin.product;

import com.laqr.NewspaperDeliverySystem.services.ProductService;
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
public class DeleteProductController {

    final ProductService productService;
    final UserService userService;
    final UserUtils userUtils;

    public DeleteProductController(ProductService productService, UserService userService, UserUtils userUtils) {
        this.productService = productService;
        this.userService = userService;
        this.userUtils = userUtils;
    }

    @PostMapping("/delete-product")
    public String deleteProduct(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("product-id") Integer productID
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            productService.deleteProduct(productID);
            redirectAttributes.addFlashAttribute("success", "Successfully Deleted Product");
            return "redirect:/admin/view-products";
        } else
            return "redirect:/";
    }
}
