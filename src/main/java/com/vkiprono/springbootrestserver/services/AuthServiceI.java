package com.vkiprono.springbootrestserver.services;

import com.vkiprono.springbootrestserver.dtos.AuthenticateRequest;
import com.vkiprono.springbootrestserver.dtos.AuthenticationResponseDTO;
import com.vkiprono.springbootrestserver.dtos.RegisterRequest;
import com.vkiprono.springbootrestserver.models.User;
import com.vkiprono.springbootrestserver.services.common.EntityMapper;

public interface AuthServiceI  extends EntityMapper<RegisterRequest, User> {
    AuthenticationResponseDTO register(RegisterRequest registerRequest);
    AuthenticationResponseDTO authenticate(AuthenticateRequest authenticateRequest);
}
