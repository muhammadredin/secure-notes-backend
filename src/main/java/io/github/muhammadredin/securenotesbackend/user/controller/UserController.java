package io.github.muhammadredin.securenotesbackend.user.controller;

import io.github.muhammadredin.securenotesbackend.user.models.LoginRequestDto;
import io.github.muhammadredin.securenotesbackend.user.models.LoginResponseDto;
import io.github.muhammadredin.securenotesbackend.user.models.User;
import io.github.muhammadredin.securenotesbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUserHandler (@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler (@RequestBody LoginRequestDto dto) {
        LoginResponseDto response = userService.login(dto);

        return ResponseEntity.ok(response);
    }
}
