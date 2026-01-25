package com.rebellion.travelblogplatform.controller;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;
import com.rebellion.travelblogplatform.service.BlogService;
import com.rebellion.travelblogplatform.service.CommentService;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("createdAt", "title", "updatedAt");

    private final BlogService blogService;
    private final CommentService commentService;

    public PublicController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }

    // ######### BLOG ENDPOINTS

    @GetMapping
    public ResponseEntity<Page<BlogResponseDto>> getAllBlog(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection) {
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field");
        }
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAllBlogs(page, size, sortBy, sortDirection));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<BlogResponseDto> getBlog(@PathVariable String slug) {
        if (slug == null || slug.isBlank())
            throw new IllegalArgumentException("Invalid URL: missing or empty slug");
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogBySlug(slug));
    }

    @GetMapping("/search")
    public Page<BlogResponseDto> searchBlogs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        if (keyword == null || keyword.trim().isEmpty())
            throw new IllegalArgumentException("Search keyword cannot be empty");
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field");
        }
        return blogService.searchBlogsByTitle(keyword, page, size, sortBy, direction);
    }

    // ######### COMMENT ENDPOINTS

    @GetMapping("/{blogId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getBlogComments(@PathVariable Long blogId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllBlogComments(blogId));
    }
}
