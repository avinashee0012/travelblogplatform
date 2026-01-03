package com.rebellion.travelblogplatform.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebellion.travelblogplatform.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
}
