package com.rebellion.travelblogplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.dto.Comment.CommentRequestDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;
import com.rebellion.travelblogplatform.entity.Comment;
import com.rebellion.travelblogplatform.repo.CommentRepo;
import com.rebellion.travelblogplatform.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepo commentRepo;

    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentResponseDto createComment(Long blogId, CommentRequestDto commentRequestDto) {
        // TODO createComment(Long blogId, CommentRequestDto commentRequestDto)
        throw new UnsupportedOperationException("Unimplemented method 'createComment'");
    }

    @Override
    public Page<Comment> getAllBlogComments(Long blogId) {
        // TODO getAllBlogComments(Long blogId)
        throw new UnsupportedOperationException("Unimplemented method 'getAllBlogComments'");
    }

    @Override
    public void deleteComment(Long commentId) {
        // TODO deleteComment(Long commentId)
        throw new UnsupportedOperationException("Unimplemented method 'deleteComment'");
    }
}
