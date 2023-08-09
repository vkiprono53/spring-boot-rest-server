package com.vkiprono.springbootrestserver.services.impl;

import com.vkiprono.springbootrestserver.dtos.AuthenticateRequest;
import com.vkiprono.springbootrestserver.dtos.AuthenticationResponseDTO;
import com.vkiprono.springbootrestserver.dtos.RegisterRequest;
import com.vkiprono.springbootrestserver.enums.Role;
import com.vkiprono.springbootrestserver.models.User;
import com.vkiprono.springbootrestserver.repositories.UserRepository;
import com.vkiprono.springbootrestserver.services.AuthServiceI;
import com.vkiprono.springbootrestserver.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthServiceI {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           JWTUtility jwtUtility,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponseDTO register(RegisterRequest registerRequest) {
        logger.info(":::::START AuthServiceImpl.register():::::");
        //DTO to Entity
        User user = this.dtoToEntity(registerRequest);

        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO();
        userRepository.save(user);
        String token = jwtUtility.generateToken(user);
        if (token != null){
            responseDTO.setToken(token);
        }
        logger.info(":::::EXIT AuthServiceImpl.register():::::");

        return responseDTO;
    }

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticateRequest authenticateRequest) {

/*
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateRequest.getEmail(), authenticateRequest.getPassword()));
        var user = userRepository.findUserByEmail(authenticateRequest.getEmail());
        var jwt = jwtUtility.generateToken(user);
        return AuthenticationResponseDTO.builder().token(jwt).build();*/


        logger.info(":::::START AuthServiceImpl.authenticate():::::");
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticateRequest.getEmail(),
                authenticateRequest.getPassword());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getEmail(), authenticateRequest.getPassword()));

        User user = userRepository.findUserByEmail(authenticateRequest.getEmail());

        if (user != null){

            String token = jwtUtility.generateToken(user);
            responseDTO.setToken(token);
        }
        logger.info(":::::EXIT AuthServiceImpl.authenticate():::::");

        return responseDTO;
    }


    @Override
    public User dtoToEntity(RegisterRequest registerRequest) {
        User user = new User();
        if (registerRequest.getFirstName() != null){
            user.setFirstName(registerRequest.getFirstName());
        }
        if (registerRequest.getLastName() != null){
            user.setLastName(registerRequest.getLastName());
        }
        if (registerRequest.getEmail() != null){
            user.setEmail(registerRequest.getEmail());
        }
        //Encrypt password
        if (registerRequest.getPassword() != null){
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        }
        user.setRole(Role.USER);

        return user;
    }

    @Override
    public RegisterRequest entityToDTO(User user) {
        return null;
    }
}
