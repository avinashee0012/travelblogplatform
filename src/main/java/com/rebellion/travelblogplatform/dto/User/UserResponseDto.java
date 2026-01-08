package com.rebellion.travelblogplatform.dto.User;

public class UserResponseDto { // response DTOs should be immutable
    private final Long id;
    private final String username;
    private final String email;
    private final String role;
    private final boolean isActive;
    
    public UserResponseDto(Long id, String username, String email, String role, boolean isActive) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

    
}
