package com.gym.demo.service.admin;

import java.util.List;
import java.util.Optional;

import com.gym.demo.models.UserEntity;

public interface AdminService {

    public Optional<UserEntity> findUserByDni(String dni);

    public List<UserEntity> findAllUsers();

}
