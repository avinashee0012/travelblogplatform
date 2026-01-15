package com.rebellion.travelblogplatform.mapper;

import com.rebellion.travelblogplatform.dto.Comment.CommentRequestDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;
import com.rebellion.travelblogplatform.entity.Blog;
import com.rebellion.travelblogplatform.entity.Comment;
import com.rebellion.travelblogplatform.entity.User;

public class CommentMapper {
    public static CommentResponseDto toResponse(Comment comment){
        return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getCommentor().getUsername(), comment.getCreatedAt());
    }

    public static Comment toEntity(CommentRequestDto commentRequestDto, Blog blog, User user){
        return new Comment(user, blog, commentRequestDto.getContent());
    }
}
