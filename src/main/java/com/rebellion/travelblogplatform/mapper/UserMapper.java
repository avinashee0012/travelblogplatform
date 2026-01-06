package com.rebellion.travelblogplatform.mapper;

import com.rebellion.travelblogplatform.dto.User.UserRegisterDto;
import com.rebellion.travelblogplatform.dto.User.UserResponseDto;
import com.rebellion.travelblogplatform.entity.User;

public class UserMapper {
    public static UserResponseDto toResponse(User user){
        // TODO Implement mapper
        return new UserResponseDto(null, null, null, null, false);
    }

    public static User fromUserRegisterDtoToEntity(UserRegisterDto userRegisterDto){
        // TODO Implement mapper
        return new User(null, null, null, null);
    }
}
