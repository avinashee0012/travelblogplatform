package com.rebellion.travelblogplatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.dto.Blog.BlogRequestDto;
import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;
import com.rebellion.travelblogplatform.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{

    @Override
    public BlogResponseDto createBlog(BlogRequestDto blogRequestDto) {
        // TODO Blog Service Method
        throw new UnsupportedOperationException("Unimplemented method 'createBlog'");
    }

    @Override
    public List<BlogResponseDto> getAllBlogs() {
        // TODO Blog Service Method
        throw new UnsupportedOperationException("Unimplemented method 'getAllBlogs'");
    }

    @Override
    public BlogResponseDto getBlogBySlug(String slug) {
        // TODO Blog Service Method
        throw new UnsupportedOperationException("Unimplemented method 'getBlogBySlug'");
    }

    @Override
    public BlogResponseDto updateBlog(Long id, BlogRequestDto blogRequestDto) {
        // TODO Blog Service Method
        throw new UnsupportedOperationException("Unimplemented method 'updateBlog'");
    }

    @Override
    public void deleteBlog(Long id) {
        // TODO Blog Service Method
        throw new UnsupportedOperationException("Unimplemented method 'deleteBlog'");
    }
}
