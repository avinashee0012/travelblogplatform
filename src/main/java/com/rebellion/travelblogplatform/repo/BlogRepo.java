package com.rebellion.travelblogplatform.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.travelblogplatform.entity.Blog;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long>{
}
