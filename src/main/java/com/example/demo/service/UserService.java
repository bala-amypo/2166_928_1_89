package com.example.demo.service;
import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;
public interface UserService extends UserDetailsService {
    User registerUser(User user);
    User findByEmail(String email);
    User getUserById(Long id);
    List<User> getAllUsers();
}