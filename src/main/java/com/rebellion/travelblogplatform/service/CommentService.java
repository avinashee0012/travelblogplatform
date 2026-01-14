package com.rebellion.travelblogplatform.service;

import org.springframework.data.domain.Page;

import com.rebellion.travelblogplatform.dto.Comment.CommentRequestDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;
import com.rebellion.travelblogplatform.entity.Comment;

public interface CommentService {
    Page<Comment> getAllBlogComments(Long blogId);
    CommentResponseDto createComment(Long blogId, CommentRequestDto commentRequestDto);
    void deleteComment(Long commentId);
}
