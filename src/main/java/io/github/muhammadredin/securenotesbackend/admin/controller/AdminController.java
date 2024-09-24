package io.github.muhammadredin.securenotesbackend.admin.controller;

import io.github.muhammadredin.securenotesbackend.admin.service.AdminService;
import io.github.muhammadredin.securenotesbackend.user.models.User;
import io.github.muhammadredin.securenotesbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<User> getAllUsersHandler() {
        return adminService.getAllUsers();
    }
}
