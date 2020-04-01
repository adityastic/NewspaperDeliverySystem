package com.laqr.NewspaperDeliverySystem.controller.admin.route;

import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ViewRouteController {

    final RouteService routeService;
    final UserService userService;
    final UserUtils userUtils;

    public ViewRouteController(RouteService routeService, UserService userService, UserUtils userUtils) {
        this.routeService = routeService;
        this.userService = userService;
        this.userUtils = userUtils;
    }

    @GetMapping("/view-route")
    public String viewRoutes(
            ModelMap model,
            HttpSession session
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("routeList", routeService.getAllRoutes());
            return "admin/route/view";
        } else
            return "redirect:/";
    }
}
