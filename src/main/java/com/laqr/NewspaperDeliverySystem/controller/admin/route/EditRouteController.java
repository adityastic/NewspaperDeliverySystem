package com.laqr.NewspaperDeliverySystem.controller.admin.route;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.RouteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class EditRouteController {

    @Autowired
    RouteService routeService;

    @Autowired
    UserService userService;

    @Autowired
    RouteUtils routeUtils;

    @GetMapping("/edit-route/{id}")
    public String editRouteGet(
            ModelMap model,
            HttpSession session,
            @PathVariable("id") Integer routeID
    ) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(username, password);
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            model.addAttribute("route", routeService.getRouteById(routeID));

            return "admin/route/edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/edit-route")
    public String editRoutePost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("route-id") Integer routeID,
            @RequestParam("route-name") String routeName
    ) {

        String currentUsername = (String) session.getAttribute("username");
        String currentPassword = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(currentUsername, currentPassword);
        if (currentUser == null)
            return "redirect:/";

        if (routeUtils.checkRouteName(routeName, routeService, redirectAttributes)) {
            routeService.editRoute(routeID, routeName);
            redirectAttributes.addFlashAttribute("success", "Successfully Edited Route ");
            return "redirect:/admin/view-route";
        }else {
            redirectAttributes.addFlashAttribute("routeStored", routeID);
            redirectAttributes.addFlashAttribute("routeStored", routeName);
            return "redirect:/admin/edit-route";
        }
    }

}
