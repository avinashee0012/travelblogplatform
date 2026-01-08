package com.rebellion.travelblogplatform.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rebellion.travelblogplatform.dto.User.UserRegisterDto;
import com.rebellion.travelblogplatform.entity.Role;
import com.rebellion.travelblogplatform.entity.User;
import com.rebellion.travelblogplatform.repo.RoleRepo;
import com.rebellion.travelblogplatform.repo.UserRepo;
import com.rebellion.travelblogplatform.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(UserService userService, UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            Role userRole = new Role("USER", "Default role with least authority");
            Role authorRole = new Role("AUTHOR", "Role assigned to an author of blog");
            Role adminRole = new Role("ADMIN", "Priviledged role for system control");

            roleRepo.save(userRole);
            roleRepo.save(authorRole);
            roleRepo.save(adminRole);

            userService.register(new UserRegisterDto("user", "user@email.com", "userPassword"));

            userService.register(new UserRegisterDto("author", "author@email.com", "authorPassword"));
            User author = userRepo.findByEmail("author@email.com").orElseThrow(() -> new EntityNotFoundException());
            author.changeRole(authorRole);
            userRepo.save(author);

            userService.register(new UserRegisterDto("admin", "admin@email.com", "adminPassword"));
            User admin = userRepo.findByEmail("admin@email.com").orElseThrow(() -> new EntityNotFoundException());
            admin.changeRole(adminRole);
            userRepo.save(admin);
        };
    }
}

