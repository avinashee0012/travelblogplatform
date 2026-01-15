package com.rebellion.travelblogplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.config.util.SecurityUtil;
import com.rebellion.travelblogplatform.dto.Comment.CommentRequestDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;
import com.rebellion.travelblogplatform.entity.Blog;
import com.rebellion.travelblogplatform.entity.Comment;
import com.rebellion.travelblogplatform.entity.User;
import com.rebellion.travelblogplatform.exception.NotLoggedInException;
import com.rebellion.travelblogplatform.mapper.CommentMapper;
import com.rebellion.travelblogplatform.repo.BlogRepo;
import com.rebellion.travelblogplatform.repo.CommentRepo;
import com.rebellion.travelblogplatform.repo.UserRepo;
import com.rebellion.travelblogplatform.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final BlogRepo blogRepo;

    public CommentServiceImpl(CommentRepo commentRepo, UserRepo userRepo, BlogRepo blogRepo) {
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.blogRepo = blogRepo;
    }

    @Override
    public CommentResponseDto createComment(Long blogId, CommentRequestDto commentRequestDto) {
        User user = userRepo.findByEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(() -> new NotLoggedInException());
        Blog blog = null;
        if(blogId != null)
            blogRepo.findById(blogId).orElseThrow(() -> new IllegalArgumentException("Invalid blog id"));
        Comment comment = CommentMapper.toEntity(commentRequestDto, blog, user);
        if(comment != null) 
            commentRepo.save(comment);
        return CommentMapper.toResponse(comment);
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
