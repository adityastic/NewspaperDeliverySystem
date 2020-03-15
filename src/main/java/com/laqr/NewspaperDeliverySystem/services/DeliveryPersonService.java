package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.DeliveryPerson;
import com.laqr.NewspaperDeliverySystem.model.Route;
import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.model.UserRole;
import com.laqr.NewspaperDeliverySystem.repository.DeliveryPersonRepository;
import com.laqr.NewspaperDeliverySystem.repository.RouteRepository;
import com.laqr.NewspaperDeliverySystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryPersonService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    DeliveryPersonRepository deliveryPersonRepository;

    public boolean checkUsername(String username) {
        return userRepository.findTopByUsername(username).isPresent();
    }

    public void addDeliveryPerson(String username, String password, String fullName, Integer routeID, String phoneNo) {
        User user = new User(username, password, UserRole.USER);
        userRepository.save(user);

        Route route = routeRepository.findById(routeID).get();

        DeliveryPerson person = new DeliveryPerson(
                user.getId(),
                user,
                fullName,
                route,
                phoneNo);
        deliveryPersonRepository.save(person);
    }

    public List<DeliveryPerson> getAllDeliveryPersons() {
        return deliveryPersonRepository.findAll();
    }

    public DeliveryPerson getDeliveryPersonById(Integer deliveryPersonId) {
        return deliveryPersonRepository.findById(deliveryPersonId).get();
    }

    public boolean checkNotThisUsername(int dpId, String username) {
        Optional<User> maybeUser = userRepository.findTopByUsername(username);
        if (maybeUser.isPresent()) {
            Optional<DeliveryPerson> maybeDP = deliveryPersonRepository.findById(dpId);
            return maybeDP.isPresent() && maybeDP.get().getId() != maybeUser.get().getId();
        }
        return false;
    }

    public void editDeliveryPerson(Integer dpId, String username, String password, String fullName, String phoneNo, Integer routeID) {
        DeliveryPerson maybePerson = deliveryPersonRepository.findById(dpId).get();

        maybePerson.getUser().setUsername(username);
        maybePerson.getUser().setPassword(password);
        maybePerson.setFullName(fullName);
        maybePerson.setPhoneNumber(phoneNo);

        Route route = routeRepository.findById(routeID).get();

        maybePerson.setRoute(route);

        deliveryPersonRepository.save(maybePerson);
    }

    public void deleteDeliveryPerson(Integer dpId) {
        deliveryPersonRepository.deleteById(dpId);
    }
}
