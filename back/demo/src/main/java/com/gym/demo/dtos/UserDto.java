package com.gym.demo.dtos;

import java.time.LocalDateTime;

import com.gym.demo.models.RoleEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;

    @NotEmpty(message = "El dni no puede estar vacío")
    private String dni;

    @NotEmpty(message = "El correo no puede estar vacío")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private String email;

    @Pattern(regexp = "\\d{8,14}", message = "El número de teléfono debe tener entre 8 y 14 dígitos")
    private String phone;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;

    @NotEmpty(message = "La fecha de creación no puede estar vacía")
    private LocalDateTime createdAt;

    private RoleEnum role;

}
