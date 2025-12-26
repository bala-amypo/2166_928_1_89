package com.example.demo.controller;
import com.example.demo.model.User; // Corrected Import
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/users") @RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register") public ResponseEntity<User> register(@RequestBody User user) { return ResponseEntity.ok(userService.registerUser(user)); }
    @GetMapping("/all") public ResponseEntity<?> getAll() { return ResponseEntity.ok(userService.getAllUsers()); }
    @GetMapping("/{id}") public ResponseEntity<?> getById(@PathVariable String id) { return ResponseEntity.ok(userService.findByEmail(id)); }
}