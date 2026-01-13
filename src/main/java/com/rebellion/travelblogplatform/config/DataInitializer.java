package com.rebellion.travelblogplatform.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rebellion.travelblogplatform.dto.Auth.UserRegisterDto;
import com.rebellion.travelblogplatform.entity.Category;
import com.rebellion.travelblogplatform.entity.Role;
import com.rebellion.travelblogplatform.entity.User;
import com.rebellion.travelblogplatform.repo.CategoryRepo;
import com.rebellion.travelblogplatform.repo.RoleRepo;
import com.rebellion.travelblogplatform.repo.UserRepo;
import com.rebellion.travelblogplatform.service.AuthService;

import jakarta.persistence.EntityNotFoundException;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(AuthService authService, UserRepo userRepo, RoleRepo roleRepo,
            CategoryRepo categoryRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if(!userRepo.existsByEmail("user@email.com"))
                setup(authService, userRepo, roleRepo, categoryRepo, passwordEncoder);
            else 
                System.out.println("Skipping setup via commandline runner");
        };
    }

    private static void setup(AuthService authService, UserRepo userRepo, RoleRepo roleRepo,
            CategoryRepo categoryRepo, PasswordEncoder passwordEncoder) {
        Role userRole = new Role("USER", "Default role with least authority");
        Role authorRole = new Role("AUTHOR", "Role assigned to an author of blog");
        Role adminRole = new Role("ADMIN", "Priviledged role for system control");

        roleRepo.findByName("USER").orElseGet(() -> roleRepo.save(userRole));
        roleRepo.findByName("AUTHOR").orElseGet(() -> roleRepo.save(authorRole));
        roleRepo.findByName("ADMIN").orElseGet(() -> roleRepo.save(adminRole));

        authService.register(new UserRegisterDto("user", "user@email.com", "userPassword"));

        authService.register(new UserRegisterDto("author", "author@email.com", "authorPassword"));
        User author = userRepo.findByEmail("author@email.com").orElseThrow(() -> new EntityNotFoundException());
        author.changeRole(authorRole);
        userRepo.save(author);

        authService.register(new UserRegisterDto("admin", "admin@email.com", "adminPassword"));
        User admin = userRepo.findByEmail("admin@email.com").orElseThrow(() -> new EntityNotFoundException());
        admin.changeRole(adminRole);
        userRepo.save(admin);

        Category beach = new Category("Beach");
        Category mountain = new Category("Mountain");
        Category nature = new Category("Nature");

        categoryRepo.save(beach);
        categoryRepo.save(mountain);
        categoryRepo.save(nature);
    }
}
