package com.gym.demo.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gym.demo.security.config.UserDetailsServiceImpl;
import com.gym.demo.security.config.Auth.AuthLoginRequest;
import com.gym.demo.security.config.Auth.AuthRegisterRequest;
import com.gym.demo.security.config.Auth.AuthResponse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequest authLoginRequest, HttpServletRequest request,
            HttpServletResponse response) {

        try {
            AuthResponse authResponse = userDetailsService.loginUser(authLoginRequest);

            Cookie jwtCookie = new Cookie("jwt", authResponse.jwt());
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(true); //
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(60 * 60); // 1 hora
            response.addCookie(jwtCookie);

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("role", authResponse.role());
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthRegisterRequest authRegisterRequest) {

        try {
            return new ResponseEntity<>(userDetailsService.registerUser(authRegisterRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/log-out")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            Arrays.stream(cookies).forEach(cookie -> {
                if (cookie.getName().equals("jwt")) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            });
        }
    
        return new ResponseEntity<>("Logout", HttpStatus.OK);
    }

    @GetMapping("/check-cookies")
    public String checkCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies).forEach(cookie -> System.out
                    .println("Cookie Name: " + cookie.getName() + ", Cookie Value: " + cookie.getValue()));
            return "Cookies recibidas";
        } else {
            return "No hay cookies";
        }
    }

}
