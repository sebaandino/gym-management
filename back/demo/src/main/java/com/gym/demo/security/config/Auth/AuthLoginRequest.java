package com.gym.demo.security.config.Auth;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest( @NotBlank String username, @NotBlank String password) {

}
