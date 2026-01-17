package com.rebellion.travelblogplatform.service.impl;

import org.springframework.stereotype.Service;

import com.rebellion.travelblogplatform.entity.Blog;
import com.rebellion.travelblogplatform.entity.Comment;
import com.rebellion.travelblogplatform.entity.Role;
import com.rebellion.travelblogplatform.entity.User;
import com.rebellion.travelblogplatform.enums.Status;
import com.rebellion.travelblogplatform.repo.BlogRepo;
import com.rebellion.travelblogplatform.repo.CommentRepo;
import com.rebellion.travelblogplatform.repo.RoleRepo;
import com.rebellion.travelblogplatform.repo.UserRepo;
import com.rebellion.travelblogplatform.service.AdminService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BlogRepo blogRepo;
    private final CommentRepo commentRepo;

    public AdminServiceImpl(
            UserRepo userRepo,
            RoleRepo roleRepo,
            BlogRepo blogRepo,
            CommentRepo commentRepo
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.blogRepo = blogRepo;
        this.commentRepo = commentRepo;
    }

    // ---------- BLOG MANAGEMENT ----------

    @Override
    public void updateBlogStatusToNeedReview(Long blogId) {
        Blog blog = blogRepo.findById(blogId)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found"));

        blog.changeStatus(Status.NEED_REVIEW);
        blogRepo.save(blog);
    }

    @Override
    public void updateBlogStatusToRemoved(Long blogId) {
        Blog blog = blogRepo.findById(blogId)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found"));

        blog.changeStatus(Status.REMOVED);
        blogRepo.save(blog);
    }

    // ---------- USER MANAGEMENT ----------

    @Override
    public void makeUserAdmin(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Role adminRole = roleRepo.findByName("ADMIN")
                .orElseThrow(() -> new EntityNotFoundException("ADMIN role not found"));

        user.changeRole(adminRole);
        userRepo.save(user);
    }

    @Override
    public void makeUserAuthor(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Role authorRole = roleRepo.findByName("AUTHOR")
                .orElseThrow(() -> new EntityNotFoundException("AUTHOR role not found"));

        user.changeRole(authorRole);
        userRepo.save(user);
    }

    // ---------- COMMENT MANAGEMENT ----------

    @Override
    public void hideComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        comment.hide(); // assumes domain method like setVisible(false)
        commentRepo.save(comment);
    }
}

