package com.rebellion.travelblogplatform.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.travelblogplatform.entity.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long>{
    Page<Comment> findByBlogIdOrderByCreatedAtDesc(Long blogId, Pageable pageable);
    @SuppressWarnings("null")
    Optional<Comment> findById(Long commentId);
}
