package com.rebellion.travelblogplatform.service;

import com.rebellion.travelblogplatform.dto.Auth.LoginResponseDto;
import com.rebellion.travelblogplatform.dto.Auth.UserLoginDto;
import com.rebellion.travelblogplatform.dto.Auth.UserRegisterDto;
import com.rebellion.travelblogplatform.dto.Auth.UserResponseDto;

public interface AuthService {
    UserResponseDto register(UserRegisterDto userRegisterDto);
    LoginResponseDto login(UserLoginDto userLoginDto);
}
