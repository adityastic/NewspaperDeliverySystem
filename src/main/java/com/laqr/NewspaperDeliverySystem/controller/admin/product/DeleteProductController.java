package com.laqr.NewspaperDeliverySystem.controller.admin.product;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.services.ProductService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class DeleteProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @PostMapping("/delete-product")
    public String deleteProduct(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("product-id") Integer productID
    ) {

        String currentUsername = (String) session.getAttribute("username");
        String currentPassword = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(currentUsername, currentPassword);
        if (currentUser == null)
            return "redirect:/";

        productService.deleteProduct(productID);
        redirectAttributes.addFlashAttribute("success", "Successfully Deleted Product");

        return "redirect:/admin/view-products";
    }
}
