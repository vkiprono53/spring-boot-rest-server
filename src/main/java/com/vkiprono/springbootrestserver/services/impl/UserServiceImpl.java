package com.vkiprono.springbootrestserver.services.impl;

import com.vkiprono.springbootrestserver.models.User;
import com.vkiprono.springbootrestserver.repositories.UserRepository;
import com.vkiprono.springbootrestserver.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceI {
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByEmail(String email) {
        logger.info(":::::START UserServiceImpl.loadUserByEmail():::::");
        if (email != null){
            return userRepository.findUserByEmail(email);
        }
        logger.info(":::::EXIT UserServiceImpl.loadUserByEmail():::::");

        return null;
    }


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findUserByEmail(username);
            }
        };
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
