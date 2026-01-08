package com.rebellion.travelblogplatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rebellion.travelblogplatform.dto.Blog.BlogRequestDto;
import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;
import com.rebellion.travelblogplatform.service.BlogService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<BlogResponseDto> createBlog(@Valid @RequestBody BlogRequestDto blogRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.createBlog(blogRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<BlogResponseDto>> getAllBlog(){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAllBlogs());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<BlogResponseDto> getBlog(@PathVariable String slug){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogBySlug(slug));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponseDto> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogRequestDto blogRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.updateBlog(id, blogRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/test")
    public String testBlogController() {
        return "BlogController: OK";
    }
}
