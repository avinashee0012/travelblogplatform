package com.rebellion.travelblogplatform.dto.User;

import java.util.List;

import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;

public class AuthorProfileResponseDto {
    private String authorName;
    // Needs object modification, will implement featuredFiveBlogs later
    // private List<BlogResponseDto> featuredFiveBlogs; 
    private List<BlogResponseDto> lastTenUpdatedBlogs;

    public AuthorProfileResponseDto() {
        // FOR JACKSON
    }

    public AuthorProfileResponseDto(String authorName, List<BlogResponseDto> lastTenUpdatedBlogs) {
        this.authorName = authorName;
        this.lastTenUpdatedBlogs = lastTenUpdatedBlogs;
    }

    public String getAuthorName() {
        return authorName;
    }

    public List<BlogResponseDto> getLastTenUpdatedBlogs() {
        return lastTenUpdatedBlogs;
    }
}
