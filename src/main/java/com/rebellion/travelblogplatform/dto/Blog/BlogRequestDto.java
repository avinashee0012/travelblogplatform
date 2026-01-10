package com.rebellion.travelblogplatform.dto.Blog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BlogRequestDto {

    @NotBlank
    @Size(min = 10, max = 100)
    private String title;

    @NotBlank
    @Size(min = 100, max = 2000)
    private String content;

    private String imageUrl; // optional field
    private String videoUrl; // optional field
    
    @NotNull
    private String categoryName;

    public BlogRequestDto() {
        // FOR JACKSON
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

    public String getCategoryName() {
        return categoryName;
    }

    // FOR JACKSON
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setCategoryId(String categoryName) {
        this.categoryName = categoryName;
    }
}
