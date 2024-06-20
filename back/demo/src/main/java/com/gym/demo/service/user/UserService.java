package com.gym.demo.service.user;

import com.gym.demo.dtos.UserEntityDto;

public interface UserService {

    UserEntityDto getUserInfo(String username);

}
