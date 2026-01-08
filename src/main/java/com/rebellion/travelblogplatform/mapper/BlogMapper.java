package com.rebellion.travelblogplatform.mapper;


import com.rebellion.travelblogplatform.dto.Blog.BlogRequestDto;
import com.rebellion.travelblogplatform.dto.Blog.BlogResponseDto;
import com.rebellion.travelblogplatform.entity.Blog;

public class BlogMapper {
    public static BlogResponseDto toResponse(Blog blog){
        // TODO Implement Blog Mapper
        return new BlogResponseDto(null, null, null, null, null, null, null, null, null);
    }

    public static Blog toEntity(BlogRequestDto blogRequestDto){
        // TODO Implement Blog Mapper
        return new Blog(null, null, null, null);
    }
}
