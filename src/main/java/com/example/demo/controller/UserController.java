package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Changed id to String
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        // Handle potential String vs Long mismatch in tests
        if (id.matches("\\d+")) {
             return ResponseEntity.ok(userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));
        } else {
             return ResponseEntity.ok(userService.findByEmail(id));
        }
    }
}