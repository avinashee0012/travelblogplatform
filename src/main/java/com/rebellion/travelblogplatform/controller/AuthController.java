package com.rebellion.travelblogplatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rebellion.travelblogplatform.dto.Auth.LoginResponseDto;
import com.rebellion.travelblogplatform.dto.Auth.UserLoginDto;
import com.rebellion.travelblogplatform.dto.Auth.UserRegisterDto;
import com.rebellion.travelblogplatform.dto.Auth.UserResponseDto;
import com.rebellion.travelblogplatform.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRegisterDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody UserLoginDto userLoginDto){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(userLoginDto));
    }

    @GetMapping("/test")
    public String testAuthController() {
        return "AuthController: OK";
    }
}
