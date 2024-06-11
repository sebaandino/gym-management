package com.gym.demo.security.config.Auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequest(@NotBlank String name, @NotBlank String dni, @NotBlank String email, @NotBlank String phone, @NotBlank String password , @Valid AuthCreateRoleRequest roleRequest ){

}
