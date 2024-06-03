package com.gym.demo.security.config.Auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequest(@NotBlank String username, @NotBlank String password, @NotBlank String dni, @NotBlank String email, @NotBlank String phone , @Valid AuthCreateRoleRequest roleRequest){

}
