package com.rebellion.travelblogplatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.config.util.SecurityUtil;
import com.rebellion.travelblogplatform.dto.Comment.CommentRequestDto;
import com.rebellion.travelblogplatform.dto.Comment.CommentResponseDto;
import com.rebellion.travelblogplatform.entity.Blog;
import com.rebellion.travelblogplatform.entity.Comment;
import com.rebellion.travelblogplatform.entity.User;
import com.rebellion.travelblogplatform.exception.NotAuthorizedException;
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
    public List<CommentResponseDto> getAllBlogComments(Long blogId) {
        if(blogId != null)
            blogRepo.findById(blogId).orElseThrow(() -> new IllegalArgumentException("Invalid blog id"));
        List<Comment> comments = commentRepo.findByBlogIdOrderByCreatedAtDesc(blogId);
        return comments.stream().map(CommentMapper::toResponse).toList();
    }

    @Override
    public void deleteComment(Long commentId) {
        User user = userRepo.findByEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(() -> new NotLoggedInException());
        Comment comment = null;
        if(commentId != null) 
            comment = commentRepo.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid comment id"));
        if(comment != null && comment.getCommentor().equals(user))
            commentRepo.delete(comment);
        else 
            throw new NotAuthorizedException("Only commentor can delete comment");
    }
}
