package com.camilosoto.prueba_tecnica.domain.services;

import com.camilosoto.prueba_tecnica.persistence.UserRepository;
import com.camilosoto.prueba_tecnica.persistence.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void updateBalance(String id, int amountChange) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBalance(user.getBalance() + amountChange);
            userRepository.save(user);
        }
    }
}
