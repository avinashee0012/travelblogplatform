package com.rebellion.travelblogplatform.dto.Blog;

import java.time.LocalDateTime;

public class BlogResponseDto { // response DTOs should be immutable
    private final Long id;
    private final String title;
    private final String content;
    private final String imageUrl;
    private final String videoUrl;
    private final String status;

    private final String authorUsername;
    private final String categoryName;
    private final LocalDateTime updatedAt;
    
    public BlogResponseDto(Long id, String title, String content, String imageUrl, String videoUrl, String status,
            String authorUsername, String categoryName, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.status = status;
        this.authorUsername = authorUsername;
        this.categoryName = categoryName;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
