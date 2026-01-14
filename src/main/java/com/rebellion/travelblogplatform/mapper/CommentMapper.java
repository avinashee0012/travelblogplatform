package com.rebellion.travelblogplatform.mapper;

import com.rebellion.travelblogplatform.dto.Comment.CommentRequestDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;
import com.rebellion.travelblogplatform.entity.Comment;

public class CommentMapper {
    public static CommentResponseDto toResponse(Comment comment){
        // TODO CommentMapper::toResponse
        return new CommentResponseDto(null, null, null, null);
    }

    public static Comment toEntity(CommentRequestDto commentRequestDto){
        // TODO CommentMapper::toEntity
        return new Comment(null, null, null);
    }
}
