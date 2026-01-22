package com.rebellion.travelblogplatform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebellion.travelblogplatform.service.AdminService;

@RestController
@RequestMapping("/api/admins/")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ---------- USER MANAGEMENT ----------
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/users/{userId}/make-author")
    public ResponseEntity<Void> makeUserAuthor(@PathVariable Long userId) {
        adminService.makeUserAuthor(userId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/users/{userId}/make-admin")
    public ResponseEntity<Void> makeUserAdmin(@PathVariable Long userId) {
        adminService.makeUserAdmin(userId);
        return ResponseEntity.noContent().build();
    }

    // ---------- BLOG MANAGEMENT ----------
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/blogs/{blogId}/status/need-review")
    public ResponseEntity<Void> markBlogNeedReview(@PathVariable Long blogId) {
        adminService.updateBlogStatusToNeedReview(blogId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/blogs/{blogId}/status/removed")
    public ResponseEntity<Void> removeBlog(@PathVariable Long blogId) {
        adminService.updateBlogStatusToRemoved(blogId);
        return ResponseEntity.noContent().build();
    }

    // ---------- COMMENT MANAGEMENT ----------

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/comments/{commentId}/hide")
    public ResponseEntity<Void> hideComment(@PathVariable Long commentId) {
        adminService.hideComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
