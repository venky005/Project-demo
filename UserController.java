package com.user.manager.controller;

import com.user.manager.model.User;
import com.user.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(userService.signup(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.login(username, password);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
        boolean success = userService.changePassword(username, oldPassword, newPassword);
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(401).build();
    }
}
