package com.rebellion.travelblogplatform.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    public UserLoginDto() {
        // FOR JACKSON
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // FOR JACKSON
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}