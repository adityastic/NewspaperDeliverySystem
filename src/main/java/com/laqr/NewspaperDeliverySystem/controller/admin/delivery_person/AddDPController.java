package com.laqr.NewspaperDeliverySystem.controller.admin.delivery_person;

import com.laqr.NewspaperDeliverySystem.services.DeliveryPersonService;
import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.DeliveryPersonUtils;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
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
public class AddDPController {

    final DeliveryPersonService deliveryPersonService;
    final UserService userService;
    final RouteService routeService;
    final DeliveryPersonUtils deliveryPersonUtils;
    final UserUtils userUtils;

    public AddDPController(DeliveryPersonService deliveryPersonService, UserService userService, RouteService routeService, DeliveryPersonUtils deliveryPersonUtils, UserUtils userUtils) {
        this.deliveryPersonService = deliveryPersonService;
        this.userService = userService;
        this.routeService = routeService;
        this.deliveryPersonUtils = deliveryPersonUtils;
        this.userUtils = userUtils;
    }

    @GetMapping("/add-delivery-persons")
    public String addDeliveryPersonsGet(
            ModelMap model,
            HttpSession session
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("routesAvailable", routeService.getAllRoutes());
            return "admin/delivery_person/add";
        } else
            return "redirect:/";
    }

    @PostMapping("/add-delivery-persons")
    public String addDeliveryPersonsPost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("full-name") String fullName,
            @RequestParam("phone-no") String phoneNo,
            @RequestParam("routeSelected") Integer routeID
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            if (deliveryPersonUtils.checkUserName(username, deliveryPersonService, redirectAttributes) &&
                    deliveryPersonUtils.checkFullName(fullName, redirectAttributes) &&
                    deliveryPersonUtils.checkPassword(password, redirectAttributes) &&
                    deliveryPersonUtils.checkPhoneNo(phoneNo, redirectAttributes)) {
                deliveryPersonService.addDeliveryPerson(username, password, fullName, routeID, phoneNo);
                redirectAttributes.addFlashAttribute("success", "Added Delivery Person");
                return "redirect:/admin/view-delivery-persons";
            } else {
                redirectAttributes.addFlashAttribute("usernameStored", username);
                redirectAttributes.addFlashAttribute("passwordStored", password);
                redirectAttributes.addFlashAttribute("phoneStored", phoneNo);
                redirectAttributes.addFlashAttribute("fullNameStored", fullName);
                return "redirect:/admin/add-delivery-persons";
            }
        } else
            return "redirect:/";
    }
}
