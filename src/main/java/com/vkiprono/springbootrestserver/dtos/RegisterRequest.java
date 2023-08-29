package com.vkiprono.springbootrestserver.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vkiprono.springbootrestserver.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private Role role;
}
