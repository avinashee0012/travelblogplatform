package com.rebellion.travelblogplatform.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.travelblogplatform.entity.Blog;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long>{
    Optional<Blog> findBySlug(String slug);
}
