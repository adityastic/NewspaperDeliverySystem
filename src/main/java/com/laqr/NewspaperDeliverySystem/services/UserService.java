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
        Optional<User> maybeUser = userRepository.findByUsername(username);
        return maybeUser.isPresent() && maybeUser.get().getPassword().equals(password);
    }

    public User getUser(String username, String password) {
        if(!checkUser(username,password)){
            return null;
        }
        return userRepository.findByUsername(username).get();
    }

    public boolean checkUsername(String username, String password){
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if(maybeUser.isPresent()) {
            User user = maybeUser.get();
            user.setPassword(password);
            userRepository.save(user);
            return true;
        }else{
            return false;
        }
    }
}
