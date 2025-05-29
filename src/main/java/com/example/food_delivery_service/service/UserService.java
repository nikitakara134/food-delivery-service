package com.example.food_delivery_service.service;

import com.example.food_delivery_service.dto.UserRegistrationDto;
import com.example.food_delivery_service.entity.User;
import com.example.food_delivery_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserRegistrationDto registrationDto) {
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }
}