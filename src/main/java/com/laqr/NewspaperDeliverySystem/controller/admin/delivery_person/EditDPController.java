package com.laqr.NewspaperDeliverySystem.controller.admin.delivery_person;

import com.laqr.NewspaperDeliverySystem.services.DeliveryPersonService;
import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.DeliveryPersonUtils;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class EditDPController {

    final DeliveryPersonService deliveryPersonService;
    final UserService userService;
    final RouteService routeService;
    final UserUtils userUtils;
    final DeliveryPersonUtils deliveryPersonUtils;

    public EditDPController(DeliveryPersonService deliveryPersonService, UserService userService, RouteService routeService, UserUtils userUtils, DeliveryPersonUtils deliveryPersonUtils) {
        this.deliveryPersonService = deliveryPersonService;
        this.userService = userService;
        this.routeService = routeService;
        this.userUtils = userUtils;
        this.deliveryPersonUtils = deliveryPersonUtils;
    }

    @GetMapping("/edit-delivery-persons/{id}")
    public String editDeliveryPersonsGet(
            ModelMap model,
            HttpSession session,
            @PathVariable("id") Integer deliveryPersonId
    ) {
        if (userUtils.isValidAdmin(session, userService, model)) {
            model.addAttribute("dp", deliveryPersonService.getDeliveryPersonById(deliveryPersonId));
            model.addAttribute("routesAvailable", routeService.getAllRoutes());
            return "admin/delivery_person/edit";
        } else
            return "redirect:/";
    }

    @PostMapping("/edit-delivery-persons")
    public String editRoutePost(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("dp-id") Integer dpId,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("full-name") String fullName,
            @RequestParam("phone-no") String phoneNo,
            @RequestParam("routeSelected") Integer routeID
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            if (deliveryPersonUtils.checkEditUserName(dpId, username, deliveryPersonService, redirectAttributes) &&
                    deliveryPersonUtils.checkFullName(fullName, redirectAttributes) &&
                    deliveryPersonUtils.checkPassword(password, redirectAttributes) &&
                    deliveryPersonUtils.checkPhoneNo(phoneNo, redirectAttributes)) {
                deliveryPersonService.editDeliveryPerson(dpId, username, password, fullName, phoneNo, routeID);
                redirectAttributes.addFlashAttribute("success", "Successfully Edited Delivery Person ");
                return "redirect:/admin/view-delivery-persons";
            } else {
                return "redirect:/admin/edit-delivery-persons/" + dpId;
            }
        } else
            return "redirect:/";
    }
}
