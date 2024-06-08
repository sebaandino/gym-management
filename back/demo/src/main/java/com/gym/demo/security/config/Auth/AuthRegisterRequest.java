package com.gym.demo.security.config.Auth;

import java.util.List;

import com.gym.demo.dtos.RutinaDiaDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequest(@NotBlank String name, @NotBlank String dni, @NotBlank String email, @NotBlank String phone, @NotBlank String password , List<RutinaDiaDto> rutinas, @Valid AuthCreateRoleRequest roleRequest ){

}
