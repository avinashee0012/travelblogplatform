package com.rebellion.travelblogplatform.dto.Comment;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private Long id;
    private String content;
    private String commentorUsername;
    private LocalDateTime createdAt;

    public CommentResponseDto() {
        // FOR JACKSON
    }

    public CommentResponseDto(Long id, String content, String commentorUsername, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.commentorUsername = commentorUsername;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCommentorUsername() {
        return commentorUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
