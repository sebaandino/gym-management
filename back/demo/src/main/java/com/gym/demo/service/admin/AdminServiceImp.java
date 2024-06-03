package com.gym.demo.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gym.demo.models.UserEntity;
import com.gym.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AdminServiceImp implements AdminService {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findUserByDni(String dni) {

        return userRepository.findUserEntityByDni("dni");
    }

    @Override
    public List<UserEntity> findAllUsers() {
        
        return userRepository.findAll();
    }

}
