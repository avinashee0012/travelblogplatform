package com.rebellion.travelblogplatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rebellion.travelblogplatform.dto.Blog.BlogRequestDto;
import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentRequestDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;
import com.rebellion.travelblogplatform.service.BlogService;
import com.rebellion.travelblogplatform.service.CommentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;
    private final CommentService commentService;

    public BlogController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @PreAuthorize("hasRole('AUTHOR')")
    @PostMapping
    public ResponseEntity<BlogResponseDto> createBlog(@Valid @RequestBody BlogRequestDto blogRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.createBlog(blogRequestDto));
    }

    @PreAuthorize("hasRole('AUTHOR')")
    @PutMapping("/{id}")
    public ResponseEntity<BlogResponseDto> updateBlog(@PathVariable Long id,
            @Valid @RequestBody BlogRequestDto blogRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.updateBlog(id, blogRequestDto));
    }

    @PreAuthorize("hasRole('AUTHOR')")
    @PatchMapping("/{id}/publish")
    public ResponseEntity<BlogResponseDto> publishBlog(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.publishBlog(id));
    }

    @PreAuthorize("hasRole('AUTHOR')")
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

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
