package com.laqr.NewspaperDeliverySystem.util;

import com.laqr.NewspaperDeliverySystem.services.RouteService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Component
public class RouteUtils {

    public boolean checkRouteName(String routeName, RouteService routeService, RedirectAttributes redirectAttributes) {
        if (routeService.checkRouteName(routeName)) {
            redirectAttributes.addFlashAttribute("error", "Route name already exists");
            return false;
        }
        if (routeName == null || routeName.trim().equals("")) {
            redirectAttributes.addFlashAttribute("error", "No Route name is Entered");
            return false;
        }
        return true;
    }
}
