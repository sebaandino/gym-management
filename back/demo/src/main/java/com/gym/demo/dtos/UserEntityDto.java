package com.gym.demo.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.gym.demo.models.Payment;
import com.gym.demo.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityDto {

    private String id;
    private String name;
    private String dni;
    private String email;
    private String phone;
    private String password;
    private LocalDateTime createdAt;
    private List<RutinaDiaDto> rutina;
    private Role role;
    private List<Payment> payments;


    private boolean isEnabled;

    private boolean accountNoLocked;

    private boolean accountNoExpired;

    private boolean credentialNoExpired;



}
