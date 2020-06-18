package com.laqr.NewspaperDeliverySystem.controller.admin.route;

import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.RouteUtils;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class EditRouteController {

    final RouteService routeService;
    final UserService userService;
    final RouteUtils routeUtils;
    final UserUtils userUtils;

    public EditRouteController(RouteService routeService, UserService userService, RouteUtils routeUtils, UserUtils userUtils) {
        this.routeService = routeService;
        this.userService = userService;
        this.routeUtils = routeUtils;
        this.userUtils = userUtils;
    }

    @GetMapping("/edit-route/{id}")
    public String editRouteGet(
            ModelMap model,
            HttpSession session,
            @PathVariable("id") Integer routeID
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("route", routeService.getRouteById(routeID));
            return "admin/route/edit";
        } else
            return "redirect:/";
    }

    @PostMapping("/edit-route")
    public String editRoutePost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("route-id") Integer routeID,
            @RequestParam("route-name") String routeName
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            if (routeUtils.checkEditRouteName(routeID, routeName, routeService, redirectAttributes)) {
                routeService.editRoute(routeID, routeName);
                redirectAttributes.addFlashAttribute("success", "Successfully Edited Route ");
                return "redirect:/admin/view-route";
            } else {
                return "redirect:/admin/edit-route/" + routeID;
            }
        } else
            return "redirect:/";
    }
}
