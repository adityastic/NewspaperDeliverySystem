package com.laqr.NewspaperDeliverySystem.controller.admin.route;

import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.RouteUtils;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AddRouteController {

    @Autowired
    RouteService routeService;

    @Autowired
    UserService userService;

    @Autowired
    RouteUtils routeUtils;

    @Autowired
    UserUtils userUtils;

    @GetMapping("/add-route")
    public String addRouteGet(
            ModelMap model,
            HttpSession session
    ) {
        if (userUtils.isValidAdmin(session, userService, model))
            return "admin/route/add";
        else
            return "redirect:/";

    }

    @PostMapping("/add-route")
    public String addRoutePost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("route-name") String routeName
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            if (routeUtils.checkRouteName(routeName, routeService, redirectAttributes)) {
                routeService.addRoute(routeName);
                redirectAttributes.addFlashAttribute("success", "Successfully Added Route");
                return "redirect:/admin/view-route";
            } else {
                redirectAttributes.addFlashAttribute("routeStored", routeName);
                return "redirect:/admin/add-route";
            }
        } else
            return "redirect:/";
    }
}
