package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    // getters & setters
}
