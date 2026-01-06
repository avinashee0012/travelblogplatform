package com.rebellion.travelblogplatform.service.impl;

import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.dto.User.UserRegisterDto;
import com.rebellion.travelblogplatform.dto.User.UserResponseDto;
import com.rebellion.travelblogplatform.entity.User;
import com.rebellion.travelblogplatform.exception.DuplicateEntryException;
import com.rebellion.travelblogplatform.mapper.UserMapper;
import com.rebellion.travelblogplatform.repo.UserRepo;
import com.rebellion.travelblogplatform.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserResponseDto register(UserRegisterDto userRegisterDto) {
        if(userRepo.findByEmail(userRegisterDto.getEmail()).isEmpty()){
            User user = UserMapper.fromUserRegisterDtoToEntity(userRegisterDto);
            userRepo.save(user);
            return UserMapper.toResponse(user);
        }
        throw new DuplicateEntryException("Duplicate entry for user: " + userRegisterDto.getEmail());
    }
    
}
