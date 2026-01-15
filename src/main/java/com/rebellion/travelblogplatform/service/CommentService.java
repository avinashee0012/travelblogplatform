package com.rebellion.travelblogplatform.service;

import java.util.List;

import com.rebellion.travelblogplatform.dto.Comment.CommentRequestDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;

public interface CommentService {
    List<CommentResponseDto> getAllBlogComments(Long blogId);
    CommentResponseDto createComment(Long blogId, CommentRequestDto commentRequestDto);
    void deleteComment(Long commentId);
}
