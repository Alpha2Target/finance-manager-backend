package com.financemanager.financemanager.service;

import com.financemanager.financemanager.exception.ResourceAlreadyExistsException;
import com.financemanager.financemanager.dto.request.LoginRequest;
import com.financemanager.financemanager.dto.request.RegisterRequest;
import com.financemanager.financemanager.dto.response.LoginResponse;
import com.financemanager.financemanager.dto.response.RegisterResponse;
import com.financemanager.financemanager.entity.User;
import com.financemanager.financemanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // REGISTER
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .build();

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                "User registered successfully",
                savedUser.getId()
        );
    }

    // LOGIN
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new ResourceAlreadyExistsException("User already exists");
        }

        return new LoginResponse("Login successful");
    }
}