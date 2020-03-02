package com.laqr.NewspaperDeliverySystem.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/admin/customer")
        public String routePage() {
            return "admin/customer";
        }

}
