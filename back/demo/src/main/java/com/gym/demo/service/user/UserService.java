package com.gym.demo.service.user;

import java.util.List;

import com.gym.demo.dtos.UserDto;

public interface UserService {

    public void save(UserDto userDto);

    public UserDto findById(Long id);

    public List<UserDto> findAll();

    public void delete(Long id);

    public UserDto update(String dni, UserDto userDto);

    public UserDto findByDni(String dni);

}
