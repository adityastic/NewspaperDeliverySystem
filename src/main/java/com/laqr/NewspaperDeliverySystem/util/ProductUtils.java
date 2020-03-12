package com.laqr.NewspaperDeliverySystem.util;

import com.laqr.NewspaperDeliverySystem.services.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class ProductUtils {

    public boolean checkProductName(String productName, ProductService productService, RedirectAttributes redirectAttributes) {
        if (productService.checkProductName(productName)) {
            redirectAttributes.addFlashAttribute("error", "Product name already exists");
            return false;
        }
        if (productName == null || productName.trim().equals("")) {
            redirectAttributes.addFlashAttribute("error", "No Product name is Entered");
            return false;
        }
        return true;
    }

    public boolean checkEditProductName(Integer productID, String productName, ProductService productService, RedirectAttributes redirectAttributes) {
        if (productService.checkNotThisProductName(productID, productName)) {
            redirectAttributes.addFlashAttribute("error", "Product name already exists");
            return false;
        }
        if (productName == null || productName.trim().equals("")) {
            redirectAttributes.addFlashAttribute("error", "No Product name is Entered");
            return false;
        }
        return true;
    }
}
