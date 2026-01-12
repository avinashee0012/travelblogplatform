package com.rebellion.travelblogplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.config.util.SecurityUtil;
import com.rebellion.travelblogplatform.dto.Blog.BlogRequestDto;
import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;
import com.rebellion.travelblogplatform.entity.Blog;
import com.rebellion.travelblogplatform.entity.Category;
import com.rebellion.travelblogplatform.entity.User;
import com.rebellion.travelblogplatform.enums.Status;
import com.rebellion.travelblogplatform.exception.NotAuthorizedException;
import com.rebellion.travelblogplatform.exception.NotLoggedInException;
import com.rebellion.travelblogplatform.mapper.BlogMapper;
import com.rebellion.travelblogplatform.repo.BlogRepo;
import com.rebellion.travelblogplatform.repo.CategoryRepo;
import com.rebellion.travelblogplatform.repo.UserRepo;
import com.rebellion.travelblogplatform.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    private final UserRepo userRepo;
    private final BlogRepo blogRepo;
    private final CategoryRepo categoryRepo;

    public BlogServiceImpl(UserRepo userRepo, BlogRepo blogRepo, CategoryRepo categoryRepo) {
        this.userRepo = userRepo;
        this.blogRepo = blogRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public BlogResponseDto createBlog(BlogRequestDto blogRequestDto) {
        User user = userRepo.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(() -> new NotLoggedInException());
        Category category = categoryRepo.findByName(blogRequestDto.getCategoryName().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category name"));
        Blog blog = BlogMapper.toEntity(blogRequestDto, user, category);
        if (blog != null)
            blogRepo.save(blog);
        return BlogMapper.toResponse(blog);
    }

    @Override
    public Page<BlogResponseDto> getAllBlogs(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc")
                                ? Sort.by(sortBy).descending()
                                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return blogRepo.findAll(pageable).map(BlogMapper::toResponse);
    }

    @Override
    public BlogResponseDto getBlogBySlug(String slug) {
        Blog blog = blogRepo.findBySlug(slug).orElseThrow(() -> new IllegalArgumentException("Invalid url"));
        return BlogMapper.toResponse(blog);
    }

    @Override
    public BlogResponseDto updateBlog(Long id, BlogRequestDto blogRequestDto) {
        User user = userRepo.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(() -> new NotLoggedInException());
        Category category = categoryRepo.findByName(blogRequestDto.getCategoryName().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category name"));
        Blog blog = null, updatedBlog = null;
        if (id != null) {
            blog = blogRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid blog id"));
            if (!blog.getAuthor().getEmail().equals(SecurityUtil.getCurrentUserEmail()))
                throw new NotAuthorizedException("Only author can update blog");
            updatedBlog = BlogMapper.toEntity(blogRequestDto, user, category);
            if (updatedBlog != null)
                blogRepo.save(updatedBlog);
        }
        return BlogMapper.toResponse(updatedBlog);
    }

    @Override
    public void deleteBlog(Long id) {
        if (id != null) {
            Blog blog = blogRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid blog id"));
            if (!blog.getAuthor().getEmail().equals(SecurityUtil.getCurrentUserEmail()))
                throw new NotAuthorizedException("Only author can update blog");
            blogRepo.delete(blog);
        }
    }

    @Override
    public BlogResponseDto publishBlog(Long id) {
        User user = userRepo.findByEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(() -> new NotLoggedInException());
        Blog blog = null;
        if (id != null) {
            blog = blogRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid blog id"));
            if (!blog.getAuthor().getEmail().equals(user.getUsername()))
                throw new NotAuthorizedException("Only author can update blog");
            blog.changeStatus(Status.PUBLISHED);
        }
        return BlogMapper.toResponse(blog);
    }
}
