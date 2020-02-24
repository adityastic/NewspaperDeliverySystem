package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.model.UserRole;
import com.laqr.NewspaperDeliverySystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String username, String password) {
        Optional<User> maybeUser = userRepository.findByUsernameAndPassword(username, password);
        if (maybeUser.isPresent() && maybeUser.get().getType() == UserRole.USER) {
            return maybeUser.get();
        } else {
            return null;
        }
    }

    public boolean changePassword(String username, String password) {
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            user.setPassword(password);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public User getAdmin(String username, String password) {
        Optional<User> maybeUser = userRepository.findByUsernameAndPassword(username, password);
        if (maybeUser.isPresent() && maybeUser.get().getType() == UserRole.ADMIN) {
            return maybeUser.get();
        } else {
            return null;
        }
    }
}
