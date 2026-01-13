package com.rebellion.travelblogplatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;
import com.rebellion.travelblogplatform.dto.User.AuthorProfileResponseDto;
import com.rebellion.travelblogplatform.entity.User;
import com.rebellion.travelblogplatform.repo.UserRepo;
import com.rebellion.travelblogplatform.service.BlogService;
import com.rebellion.travelblogplatform.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final BlogService blogService;

    public UserServiceImpl(UserRepo userRepo, BlogService blogService) {
        this.userRepo = userRepo;
        this.blogService = blogService;
    }

    @Override
    public AuthorProfileResponseDto getAuthorProfile(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(username));
        if(user.getRole().getName().equals("AUTHOR")){
            throw new IllegalArgumentException("Not an author profile");
        }
        List<BlogResponseDto> lastTenBlogs = blogService.getTenLastUpdatedBlogs(user.getUsername());
        return new AuthorProfileResponseDto(user.getUsername(), lastTenBlogs);
    }
}
