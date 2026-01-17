package com.rebellion.travelblogplatform.service;

public interface AdminService {
    void updateBlogStatusToNeedReview(Long blogId);
    void makeUserAdmin(Long userId);
    void updateBlogStatusToRemoved(Long blogId);
    void hideComment(Long commentId);
    void makeUserAuthor(Long userId);
}
