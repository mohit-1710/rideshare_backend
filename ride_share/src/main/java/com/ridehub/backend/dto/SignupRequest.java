package com.ridehub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    @Size(min = 3, message = "Username must be at least 3 chars")
    private String username;

    @NotBlank
    @Size(min = 4, message = "Password must be at least 4 chars")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;
}
