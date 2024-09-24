package io.github.muhammadredin.securenotesbackend.user.service;

import io.github.muhammadredin.securenotesbackend.user.models.Role;
import io.github.muhammadredin.securenotesbackend.user.models.User;
import io.github.muhammadredin.securenotesbackend.user.models.UserRole;
import io.github.muhammadredin.securenotesbackend.user.repositories.RoleRepository;
import io.github.muhammadredin.securenotesbackend.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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

        Role userRole = roleRepository.findByRoleName(UserRole.ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Role(UserRole.ROLE_USER)));

        user.setPassword(encoder.encode(user.getPassword()));
        user.setAccountNonLocked(false);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
        user.setAccountExpiryDate(LocalDate.now().plusYears(1));
        user.setSignUpMethod("email");
        user.setRole(userRole);

        return userRepository.save(user);
    }
}
