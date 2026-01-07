package com.rebellion.travelblogplatform.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rebellion.travelblogplatform.entity.Role;
import com.rebellion.travelblogplatform.repo.RoleRepo;

@Configuration
public class RoleDataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepo roleRepo) {
        return args -> {

            createRoleIfNotExists(roleRepo, "USER", "Default role with least authority");
            createRoleIfNotExists(roleRepo, "AUTHOR", "Role assigned to an author of blog");
            createRoleIfNotExists(roleRepo, "ADMIN", "Priviledged role for system control");

        };
    }

    private void createRoleIfNotExists(RoleRepo roleRepo, String roleName, String description) {
        roleRepo.findByName(roleName)
                .orElseGet(() -> roleRepo.save(new Role(roleName, description)));
    }
}

