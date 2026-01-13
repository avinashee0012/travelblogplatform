package com.rebellion.travelblogplatform.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.config.jwt.JwtUtil;
import com.rebellion.travelblogplatform.dto.Auth.LoginResponseDto;
import com.rebellion.travelblogplatform.dto.Auth.UserLoginDto;
import com.rebellion.travelblogplatform.dto.Auth.UserRegisterDto;
import com.rebellion.travelblogplatform.dto.Auth.UserResponseDto;
import com.rebellion.travelblogplatform.entity.Role;
import com.rebellion.travelblogplatform.entity.User;
import com.rebellion.travelblogplatform.exception.DuplicateEntryException;
import com.rebellion.travelblogplatform.exception.InactiveUserException;
import com.rebellion.travelblogplatform.mapper.UserMapper;
import com.rebellion.travelblogplatform.repo.RoleRepo;
import com.rebellion.travelblogplatform.repo.UserRepo;
import com.rebellion.travelblogplatform.service.AuthService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto register(UserRegisterDto userRegisterDto) {
        if (userRepo.existsByEmail(userRegisterDto.getEmail())) {
            throw new DuplicateEntryException("Duplicate entry for user: " + userRegisterDto.getEmail());
        }
        String encodedPassword = passwordEncoder.encode(userRegisterDto.getPassword());
        Role userRole = roleRepo.findByName("USER")
                .orElseThrow(() -> new EntityNotFoundException("Default role USER doesn't exist"));
        User user = UserMapper.fromUserRegisterDtoToEntity(userRegisterDto, encodedPassword, userRole);
        if (user != null)
            userRepo.save(user);
        return UserMapper.toResponse(user);
    }

    @Override
    public LoginResponseDto login(UserLoginDto userLoginDto) {
        User user = userRepo.findByEmail(userLoginDto.getEmail()).orElseThrow(() -> new EntityNotFoundException("Invalid Login Credential"));
        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword()))
            throw new EntityNotFoundException("Invalid Login Credential"); 
        if(!user.isActive())
            throw new InactiveUserException();
        return new LoginResponseDto(JwtUtil.generateToken(user.getEmail(), user.getRole()));
    }
}
