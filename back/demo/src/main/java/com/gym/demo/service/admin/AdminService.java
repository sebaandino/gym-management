package com.gym.demo.service.admin;

import java.util.List;

import com.gym.demo.dtos.UserEntityDto;

public interface AdminService {

    void save(UserEntityDto userEntityDto);

    UserEntityDto findByDni(String dni);

    List<UserEntityDto> findAll();

    String findRutinaByDni(String dni);

    void delete(String id);

}
