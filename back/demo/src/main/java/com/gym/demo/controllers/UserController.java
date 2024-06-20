package com.gym.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.demo.dtos.UserEntityDto;
import com.gym.demo.service.user.UserServiceImp;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @GetMapping("/getInfo")
    public ResponseEntity<?> getUserInfo( HttpServletRequest request) { 

        Cookie[] cookies = request.getCookies();
        String jwt = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
    }

    try {
        UserEntityDto userEntityDto = userService.getUserInfo(jwt);
        return new ResponseEntity<>(userEntityDto, HttpStatus.OK);
    } catch (Exception e) {
        return ResponseEntity.badRequest().build();
    }
    

}
}
