package com.rebellion.travelblogplatform.service.impl;

import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.dto.User.AuthorProfileResponseDto;
import com.rebellion.travelblogplatform.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public AuthorProfileResponseDto getAuthorProfile(String username) {
        // TODO getUserProfile(String username)

        // Public author profile for visitors
        // Includes upto 5 featured posts
        // last 10 posts

        return null;
    }
}
