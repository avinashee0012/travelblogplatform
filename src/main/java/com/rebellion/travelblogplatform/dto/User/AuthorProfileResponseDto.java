package com.rebellion.travelblogplatform.dto.User;

import java.util.List;

import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;

public class AuthorProfileResponseDto {
    private String authorName;
    private List<BlogResponseDto> featuredFiveBlogs;
    private List<BlogResponseDto> lastTenUpdatedBlogs;

    public AuthorProfileResponseDto() {
        // FOR JACKSON
    }

    public AuthorProfileResponseDto(String authorName, List<BlogResponseDto> featuredFiveBlogs,
            List<BlogResponseDto> lastTenUpdatedBlogs) {
        this.authorName = authorName;
        this.featuredFiveBlogs = featuredFiveBlogs;
        this.lastTenUpdatedBlogs = lastTenUpdatedBlogs;
    }

    public String getAuthorName() {
        return authorName;
    }

    public List<BlogResponseDto> getFeaturedFiveBlogs() {
        return List.copyOf(featuredFiveBlogs);
    }

    public List<BlogResponseDto> getLastTenUpdatedBlogs() {
        return List.copyOf(lastTenUpdatedBlogs);
    }
}
