package com.rebellion.travelblogplatform.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.travelblogplatform.entity.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long>{
}
