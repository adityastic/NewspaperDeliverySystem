package com.laqr.NewspaperDeliverySystem.controller.admin;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.services.DeliveryPersonService;
import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
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
public class DeliveryManageController {

    @Autowired
    DeliveryPersonService deliveryPersonService;

    @Autowired
    UserService userService;

    @Autowired
    RouteService routeService;

    @Autowired
    UserRegistrationUtils userRegistrationUtils;

    @GetMapping("/register-delivery-persons")
    public String registerDeliveryPersonsHome(
            ModelMap model,
            HttpSession session
    ) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(username, password);
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            model.addAttribute("routesAvailable", routeService.getAllRoutes());
            return "admin/register";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/register-delivery-persons")
    public String registerDeliveryPersons(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("full-name") String fullName,
            @RequestParam("phone-no") String phoneNo,
            @RequestParam("routeSelected") Integer routeID
    ) {

        String currentUsername = (String) session.getAttribute("username");
        String currentPassword = (String) session.getAttribute("password");

        User currentUser = userService.getAdmin(currentUsername, currentPassword);
        if (currentUser == null)
            return "redirect:/";

        if (userRegistrationUtils.checkUserName(username, deliveryPersonService, redirectAttributes) &&
                userRegistrationUtils.checkFullName(fullName, redirectAttributes) &&
                userRegistrationUtils.checkPassword(password, redirectAttributes) &&
                userRegistrationUtils.checkPhoneNo(phoneNo, redirectAttributes)) {
            deliveryPersonService.addDeliveryPerson(username, password, fullName, routeID, phoneNo);
            redirectAttributes.addFlashAttribute("success", "Added Delivery Person");
        } else {
            redirectAttributes.addFlashAttribute("usernameStored", username);
            redirectAttributes.addFlashAttribute("passwordStored", password);
            redirectAttributes.addFlashAttribute("phoneStored", phoneNo);
            redirectAttributes.addFlashAttribute("fullNameStored", fullName);
        }

        return "redirect:/admin/register-delivery-persons";
    }
}
