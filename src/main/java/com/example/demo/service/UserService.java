package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {

    User registerUser(User user);

    List<User> getAllUsers();   // ✅ REQUIRED BY TESTS
}
