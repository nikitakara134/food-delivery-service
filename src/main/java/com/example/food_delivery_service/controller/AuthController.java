package com.example.food_delivery_service.controller;

import com.example.food_delivery_service.dto.AuthRequest;
import com.example.food_delivery_service.dto.AuthResponse;
import com.example.food_delivery_service.dto.UserRegistrationDto;
import com.example.food_delivery_service.service.AuthService;
import com.example.food_delivery_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDto registrationDto) {
        userService.registerUser(registrationDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}