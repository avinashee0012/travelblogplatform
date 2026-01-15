package com.rebellion.travelblogplatform.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.travelblogplatform.entity.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long>{
    List<Comment> findByBlogIdOrderByCreatedAtDesc(Long blogId);
}
