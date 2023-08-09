package com.vkiprono.springbootrestserver.restcontrollers;

import com.vkiprono.springbootrestserver.dtos.AuthenticateRequest;
import com.vkiprono.springbootrestserver.dtos.AuthenticationResponseDTO;
import com.vkiprono.springbootrestserver.dtos.RegisterRequest;
import com.vkiprono.springbootrestserver.services.AuthServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServiceI authServiceI;

    @Autowired
    public AuthController(AuthServiceI authServiceI) {
        this.authServiceI = authServiceI;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequest registerRequest){


        return ResponseEntity.ok(authServiceI.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticateRequest authenticateRequest){

       return ResponseEntity.ok(authServiceI.authenticate(authenticateRequest));
    }


}
