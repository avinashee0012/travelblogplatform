package com.rebellion.travelblogplatform.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.travelblogplatform.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{
}
