package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired

    UserRepository userRepository;

    public boolean checkUser(String username, String password) {
        Optional<User> maybeUser = userRepository.findByUsernameAndPassword(username, password);
        return maybeUser.isPresent();
    }

    public User getUser(String username, String password) {
        if (!checkUser(username, password)) {
            return null;
        }
        return userRepository.findByUsernameAndPassword(username, password).get();
    }

    public boolean checkUsername(String username, String password) {
        Optional<User> maybeUser = userRepository.findByUsernameAndPassword(username, password);
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            user.setPassword(password);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
