package com.rebellion.travelblogplatform.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rebellion.travelblogplatform.dto.Blog.BlogRequestDto;
import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;

public interface BlogService {
    BlogResponseDto createBlog(BlogRequestDto blogRequestDto);
    Page<BlogResponseDto> getAllBlogs(int page, int size, String sortBy, String sortDirection);
    BlogResponseDto getBlogBySlug(String slug);
    BlogResponseDto updateBlog(Long id, BlogRequestDto blogRequestDto);
    void deleteBlog(Long id);
    BlogResponseDto publishBlog(Long id);
    // List<BlogResponseDto> getFiveFeaturedBlogs(String validUsername);
    List<BlogResponseDto> getTenLastUpdatedBlogs(String validUsername);
}
