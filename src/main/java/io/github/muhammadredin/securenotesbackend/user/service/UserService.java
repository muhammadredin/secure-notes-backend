package io.github.muhammadredin.securenotesbackend.user.service;

import io.github.muhammadredin.securenotesbackend.common.response.MessageResponse;
import io.github.muhammadredin.securenotesbackend.security.services.JwtService;
import io.github.muhammadredin.securenotesbackend.user.dto.LoginRequestDto;
import io.github.muhammadredin.securenotesbackend.user.dto.LoginResponseDto;
import io.github.muhammadredin.securenotesbackend.user.models.*;
import io.github.muhammadredin.securenotesbackend.user.repositories.RoleRepository;
import io.github.muhammadredin.securenotesbackend.user.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public MessageResponse createUser(HttpServletRequest request, User user) {
        List<String> errors = new ArrayList<>();

        User checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser != null) {
            errors.add("Username is already taken");
        }

        checkUser = userRepository.findByEmail(user.getEmail());
        if (checkUser != null) {
            errors.add("Email is already taken");
        }

        if (!errors.isEmpty()) {
            return new MessageResponse(
                    request.getServletPath(),
                    "Bad Request",
                    errors,
                    400
            );
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

        userRepository.save(user);

        return new MessageResponse(
                request.getServletPath(),
                "User Successfully Created",
                errors,
                201
        );
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        if (authentication.isAuthenticated()) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(user);

            List<String> roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            return new LoginResponseDto(token, user.getUsername(), roles);
        }

        throw new BadCredentialsException("Bad credentials");
    }
}
