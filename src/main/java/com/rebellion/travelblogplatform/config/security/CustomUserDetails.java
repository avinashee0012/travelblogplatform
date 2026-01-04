package com.rebellion.travelblogplatform.config.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rebellion.travelblogplatform.entity.User;

// Spring Security works with UserDetails, not your entity directly.
public class CustomUserDetails implements UserDetails{

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE" + user.getRole().getName()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();    
    }

    @Override
    public String getUsername() { // username for login --> email here
        return user.getEmail();
    }
}
