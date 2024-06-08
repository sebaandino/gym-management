package com.gym.demo.security.config.Auth;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;

@Validated
public record AuthCreateRoleRequest(
    @Size(max = 1 , message = "se puedo otorgar solo 1 rol") List<String> roleName){

    }
