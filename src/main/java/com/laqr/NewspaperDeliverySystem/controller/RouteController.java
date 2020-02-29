package com.laqr.NewspaperDeliverySystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

    @GetMapping("/admin/route")
    public String routePage() {
        return "admin/route";
    }
}
