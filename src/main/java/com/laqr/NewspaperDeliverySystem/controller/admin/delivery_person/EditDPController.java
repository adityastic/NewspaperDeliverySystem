package com.laqr.NewspaperDeliverySystem.controller.admin.delivery_person;

import com.laqr.NewspaperDeliverySystem.services.DeliveryPersonService;
import com.laqr.NewspaperDeliverySystem.services.RouteService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class EditDPController {

    @Autowired
    DeliveryPersonService deliveryPersonService;

    @Autowired
    UserService userService;

    @Autowired
    RouteService routeService;

    @Autowired
    UserUtils userUtils;

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
}
