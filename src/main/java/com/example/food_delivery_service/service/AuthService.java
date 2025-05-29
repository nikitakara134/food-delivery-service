package com.example.food_delivery_service.service;

import com.example.food_delivery_service.dto.AuthRequest;
import com.example.food_delivery_service.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        UserDetails user = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getAuthorities().iterator().next().getAuthority())
                .build();
    }
}