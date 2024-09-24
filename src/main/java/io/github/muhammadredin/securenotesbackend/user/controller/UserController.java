package io.github.muhammadredin.securenotesbackend.user.controller;

import io.github.muhammadredin.securenotesbackend.user.dto.LoginRequestDto;
import io.github.muhammadredin.securenotesbackend.user.dto.LoginResponseDto;
import io.github.muhammadredin.securenotesbackend.common.response.MessageResponse;
import io.github.muhammadredin.securenotesbackend.user.models.User;
import io.github.muhammadredin.securenotesbackend.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUserHandler (@Valid @RequestBody User user, HttpServletRequest request) {
        MessageResponse response = userService.createUser(request, user);
        if (response.getErrors().isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler (@RequestBody LoginRequestDto dto) {
        LoginResponseDto response = userService.login(dto);

        return ResponseEntity.ok(response);
    }
}
