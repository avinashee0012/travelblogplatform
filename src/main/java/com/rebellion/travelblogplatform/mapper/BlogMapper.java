package com.rebellion.travelblogplatform.mapper;

import com.rebellion.travelblogplatform.dto.Blog.BlogRequestDto;
import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;
import com.rebellion.travelblogplatform.entity.Blog;
import com.rebellion.travelblogplatform.entity.Category;
import com.rebellion.travelblogplatform.entity.User;

public class BlogMapper {

    public static BlogResponseDto toResponse(Blog blog){
        return new BlogResponseDto(blog.getId(), blog.getTitle(), blog.getContent(), blog.getImageUrl(), blog.getVideoUrl(), blog.getStatus().name(), blog.getSlug(), blog.getAuthor().getUsername(), blog.getCategory().getName(), blog.getUpdatedAt());
    }

    public static Blog toEntity(BlogRequestDto blogRequestDto, User author, Category category){
        return new Blog(blogRequestDto.getTitle(), blogRequestDto.getContent(), blogRequestDto.getImageUrl(), blogRequestDto.getVideoUrl(), author, category);
    }
}
