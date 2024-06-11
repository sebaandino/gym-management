package com.gym.demo.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gym.demo.dtos.RutinaDiaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;

    private String name;
    private String dni;
    private String email;
    private String phone;
    private String password;
    private LocalDateTime createdAt;
    private List<RutinaDiaDto> rutina;
    @DBRef
    private Role role;
    private List<Payment> payments;

    private boolean isEnabled;

    private boolean accountNoLocked;

    private boolean accountNoExpired;

    private boolean credentialNoExpired;


}
