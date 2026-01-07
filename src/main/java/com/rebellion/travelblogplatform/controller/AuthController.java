package com.rebellion.travelblogplatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rebellion.travelblogplatform.dto.User.UserRegisterDto;
import com.rebellion.travelblogplatform.dto.User.UserResponseDto;
import com.rebellion.travelblogplatform.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userRegisterDto));
    }

    @GetMapping("/test")
    public String testAuthController() {
        return "AuthController: OK";
    }
}
