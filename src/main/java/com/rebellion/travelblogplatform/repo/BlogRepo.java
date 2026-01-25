package com.rebellion.travelblogplatform.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.travelblogplatform.entity.Blog;
import com.rebellion.travelblogplatform.enums.Status;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long>{
    Optional<Blog> findBySlug(String slug);
    List<Blog> findTop10ByAuthorUsernameAndStatusOrderByUpdatedAtDesc(String username, Status status);
    Page<Blog> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
