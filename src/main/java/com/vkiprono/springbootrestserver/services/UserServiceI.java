package com.vkiprono.springbootrestserver.services;

import com.vkiprono.springbootrestserver.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceI extends UserDetailsService {
    User loadUserByEmail(String email);

    UserDetailsService userDetailsService();
}
