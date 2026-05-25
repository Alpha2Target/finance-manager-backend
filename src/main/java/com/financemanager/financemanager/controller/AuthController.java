package com.financemanager.financemanager.controller;

import com.financemanager.financemanager.dto.request.LoginRequest;
import com.financemanager.financemanager.dto.request.RegisterRequest;
import com.financemanager.financemanager.dto.response.LoginResponse;
import com.financemanager.financemanager.dto.response.RegisterResponse;
import com.financemanager.financemanager.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // REGISTER API
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {

        return authService.register(request);
    }

    // LOGIN API
    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {

        return authService.login(request);
    }
}