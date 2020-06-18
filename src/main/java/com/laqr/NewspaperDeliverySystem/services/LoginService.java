package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.User;
import com.laqr.NewspaperDeliverySystem.model.UserRole;
import com.laqr.NewspaperDeliverySystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUser(String username, String password) {
        Optional<User> maybeUser = userRepository.findTopByUsernameAndPassword(username, password);
        return maybeUser.isPresent() && maybeUser.get().getType() == UserRole.USER;
    }

    public boolean checkAdmin(String username, String password) {
        Optional<User> maybeUser = userRepository.findTopByUsernameAndPassword(username, password);
        return maybeUser.isPresent() && maybeUser.get().getType() == UserRole.ADMIN;
    }
}
