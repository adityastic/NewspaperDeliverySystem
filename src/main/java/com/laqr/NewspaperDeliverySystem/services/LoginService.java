package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public boolean checkUser(String username, String password) {
        Optional<User> maybeUser = userRepository.findByUsername(username);
        return maybeUser.isPresent() && maybeUser.get().getPassword().equals(password);
    }
}
