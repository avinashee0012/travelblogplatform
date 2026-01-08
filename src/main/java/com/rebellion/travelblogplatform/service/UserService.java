package com.rebellion.travelblogplatform.service;

import com.rebellion.travelblogplatform.dto.User.LoginResponseDto;
import com.rebellion.travelblogplatform.dto.User.UserLoginDto;
import com.rebellion.travelblogplatform.dto.User.UserRegisterDto;
import com.rebellion.travelblogplatform.dto.User.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegisterDto userRegisterDto);
    LoginResponseDto login(UserLoginDto userLoginDto);
}
