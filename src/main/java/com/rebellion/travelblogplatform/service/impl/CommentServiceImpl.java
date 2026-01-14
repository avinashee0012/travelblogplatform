package com.rebellion.travelblogplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.dto.Comment.CommentRequestDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;
import com.rebellion.travelblogplatform.entity.Comment;
import com.rebellion.travelblogplatform.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

    @Override
    public Page<Comment> getAllBlogComments(Long blogId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllBlogComments'");
    }

    @Override
    public CommentResponseDto createComment(Long blogId, CommentRequestDto commentRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createComment'");
    }

    @Override
    public void deleteComment(Long commentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteComment'");
    }
    
}
