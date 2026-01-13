package com.rebellion.travelblogplatform.service;

import com.rebellion.travelblogplatform.dto.User.AuthorProfileResponseDto;

public interface UserService {
    AuthorProfileResponseDto getAuthorProfile(String username);
}
