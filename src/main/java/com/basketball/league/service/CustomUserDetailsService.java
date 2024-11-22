package com.basketball.league.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Replace with actual database calls
        if ("user".equals(username)) {
            return new User("user", new BCryptPasswordEncoder().encode("password"), Collections.emptyList());
        } else if ("admin".equals(username)) {
            return new User("admin", new BCryptPasswordEncoder().encode("password"), Collections.singleton(() -> "ROLE_ADMIN"));
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}