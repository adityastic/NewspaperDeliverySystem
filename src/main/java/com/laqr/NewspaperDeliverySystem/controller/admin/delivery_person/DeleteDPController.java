package com.laqr.NewspaperDeliverySystem.controller.admin.delivery_person;

import com.laqr.NewspaperDeliverySystem.services.DeliveryPersonService;
import com.laqr.NewspaperDeliverySystem.services.UserService;
import com.laqr.NewspaperDeliverySystem.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class DeleteDPController {

    @Autowired
    DeliveryPersonService deliveryPersonService;

    @Autowired
    UserService userService;

    @Autowired
    UserUtils userUtils;

    @PostMapping("/delete-delivery-persons")
    public String deleteProduct(
            RedirectAttributes redirectAttributes,
            HttpSession session,
            @RequestParam("dp-id") Integer dpId
    ) {
        if (userUtils.isValidAdmin(session, userService, null)) {
            deliveryPersonService.deleteDeliveryPerson(dpId);
            redirectAttributes.addFlashAttribute("success", "Successfully Deleted Delivery Person");
            return "redirect:/admin/view-delivery-persons";
        } else
            return "redirect:/";
    }
}
