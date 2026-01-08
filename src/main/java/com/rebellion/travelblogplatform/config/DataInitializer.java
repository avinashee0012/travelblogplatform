package com.rebellion.travelblogplatform.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rebellion.travelblogplatform.entity.Role;
import com.rebellion.travelblogplatform.entity.User;
import com.rebellion.travelblogplatform.repo.RoleRepo;
import com.rebellion.travelblogplatform.repo.UserRepo;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(UserRepo userRepo, RoleRepo roleRepo) {
        return args -> {
            Role userRole = new Role("USER", "Default role with least authority");
            Role authorRole = new Role("AUTHOR", "Role assigned to an author of blog");
            Role adminRole = new Role("ADMIN", "Priviledged role for system control");

            roleRepo.save(userRole);
            roleRepo.save(authorRole);
            roleRepo.save(adminRole);

            User user = new User("user", "user@email.com", "userPassword", userRole);
            User author = new User("author", "author@email.com", "authorPassword", authorRole);
            User admin = new User("admin", "admin@email.com", "adminPassword", adminRole);

            userRepo.save(user);
            userRepo.save(author);
            userRepo.save(admin);
        };
    }
}

