package com.rebellion.travelblogplatform.mapper;


import com.rebellion.travelblogplatform.dto.User.UserRegisterDto;
import com.rebellion.travelblogplatform.dto.User.UserResponseDto;
import com.rebellion.travelblogplatform.entity.Role;
import com.rebellion.travelblogplatform.entity.User;

public class UserMapper {
    public static UserResponseDto toResponse(User user){
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole().getName(), user.isActive());
    }

    public static User fromUserRegisterDtoToEntity(UserRegisterDto userRegisterDto, String encodedPassword, Role role){
        return new User(userRegisterDto.getUsername(), userRegisterDto.getEmail(), encodedPassword, role);
    }
}
