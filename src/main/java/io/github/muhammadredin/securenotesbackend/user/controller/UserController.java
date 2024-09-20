package io.github.muhammadredin.securenotesbackend.user.controller;

import io.github.muhammadredin.securenotesbackend.user.models.User;
import io.github.muhammadredin.securenotesbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUserHandler (@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}
