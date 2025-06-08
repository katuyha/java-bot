package ru.stankevich.teaching.java_bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ru.stankevich.teaching.java_bot.service.UserService;


@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestParam String username,
                                         @RequestParam String password) {
        userService.register(username, password);
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{username}/role")
    public ResponseEntity<String> getUserRole(@PathVariable String username) {
        String role = userService.getRole(username);
        return ResponseEntity.ok(role);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/{username}/role")
    public ResponseEntity<Void> updateUserRole(@PathVariable String username,
                                               @RequestParam String roleName) {
        userService.updateRole(username, roleName);
        return ResponseEntity.ok().build();
    }

}
