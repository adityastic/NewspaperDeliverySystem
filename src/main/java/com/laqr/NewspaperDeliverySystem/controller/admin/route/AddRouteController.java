package com.laqr.NewspaperDeliverySystem.controller.admin.route;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.RouteUtils;
import com.laqr.NewspaperDeliverySystem.util.UserRegistrationUtils;
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

    @GetMapping("/add-route")
    public String addRouteGet(
            ModelMap model,
            HttpSession session
    ) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(username, password);
        if (currentUser != null) {
            model.addAttribute(currentUser);
            return "admin/route/add";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add-route")
    public String addRoutePost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("route-name") String routeName
    ) {

        String currentUsername = (String) session.getAttribute("username");
        String currentPassword = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(currentUsername, currentPassword);
        if (currentUser == null)
            return "redirect:/";

        if(routeUtils.checkRouteName(routeName, routeService, redirectAttributes)){
            routeService.addRoute(routeName);
            redirectAttributes.addFlashAttribute("success","Successfully Added Route");
            return "redirect:/admin/view-route";
        }else{
            redirectAttributes.addFlashAttribute("routeStored", routeName);
            return "redirect:/admin/add-route";
        }
    }
}
