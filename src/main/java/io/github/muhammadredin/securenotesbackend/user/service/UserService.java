package io.github.muhammadredin.securenotesbackend.user.service;

import io.github.muhammadredin.securenotesbackend.user.models.User;
import io.github.muhammadredin.securenotesbackend.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
        User checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser != null) {
            throw new RuntimeException("Username already exists");
        }

        checkUser = userRepository.findByUsername(user.getEmail());
        if (checkUser != null) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
