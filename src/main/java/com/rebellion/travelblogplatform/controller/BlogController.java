package com.rebellion.travelblogplatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rebellion.travelblogplatform.dto.Blog.BlogRequestDto;
import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentRequestDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;
import com.rebellion.travelblogplatform.service.BlogService;
import com.rebellion.travelblogplatform.service.CommentService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("createdAt", "title", "updatedAt");

    private final BlogService blogService;
    private final CommentService commentService;

    public BlogController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<BlogResponseDto> createBlog(@Valid @RequestBody BlogRequestDto blogRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.createBlog(blogRequestDto));
    }

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
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogBySlug(slug));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponseDto> updateBlog(@PathVariable Long id,
            @Valid @RequestBody BlogRequestDto blogRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.updateBlog(id, blogRequestDto));
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<BlogResponseDto> publishBlog(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.publishBlog(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // ######### COMMENT ENDPOINTS

    @PostMapping("/{blogId}/comments")
    public ResponseEntity<CommentResponseDto> postComment(@PathVariable Long blogId, @RequestBody CommentRequestDto commentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(blogId, commentRequestDto));
    }

    @GetMapping("/{blogId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getBlogComments(@PathVariable Long blogId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllBlogComments(blogId));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // ######### TEST ENDPOINT

    @GetMapping("/test")
    public String testBlogController() {
        return "BlogController: OK";
    }
}
