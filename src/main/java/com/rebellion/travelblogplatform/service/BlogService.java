package com.rebellion.travelblogplatform.service;

import java.util.List;

import com.rebellion.travelblogplatform.dto.Blog.BlogRequestDto;
import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;

public interface BlogService {
    BlogResponseDto createBlog(BlogRequestDto blogRequestDto);
    List<BlogResponseDto> getAllBlogs();
    BlogResponseDto getBlogBySlug(String slug);
    BlogResponseDto updateBlog(Long id, BlogRequestDto blogRequestDto);
    void deleteBlog(Long id);
}
