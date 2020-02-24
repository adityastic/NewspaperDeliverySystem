package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.DeliveryPerson;
import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.model.UserRole;
import com.laqr.NewspaperDeliverySystem.repository.DeliveryPersonRepository;
import com.laqr.NewspaperDeliverySystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPersonService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeliveryPersonRepository deliveryPersonRepository;

    public boolean checkUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public void addDeliveryPerson(String username, String password, String fullName, String phoneNo) {
        User user = new User(username, password, UserRole.USER);
        userRepository.save(user);
        DeliveryPerson person = new DeliveryPerson(
                user.getId(),
                user,
                fullName,
                phoneNo);
        deliveryPersonRepository.save(person);
    }
}
