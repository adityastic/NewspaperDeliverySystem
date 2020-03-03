package com.laqr.NewspaperDeliverySystem.controller.admin.route;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.services.RouteService;
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
public class DeleteRouteController {

    @Autowired
    RouteService routeService;

    @Autowired
    UserService userService;

    @PostMapping("/delete-route")
    public String deleteRoute(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("route-id") Integer routeID
    ) {

        String currentUsername = (String) session.getAttribute("username");
        String currentPassword = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(currentUsername, currentPassword);
        if (currentUser == null)
            return "redirect:/";

        routeService.deleteRoute(routeID);
        redirectAttributes.addFlashAttribute("success", "Successfully Deleted Route");

        return "redirect:/admin/view-route";
    }
}
