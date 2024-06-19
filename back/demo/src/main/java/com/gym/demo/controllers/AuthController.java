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
import com.gym.demo.security.config.Auth.AuthResponse;
import com.gym.demo.security.config.jwt.JwtUtil;

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

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequest authLoginRequest, HttpServletRequest request,
            HttpServletResponse response) {

        try {
            AuthResponse authResponse = userDetailsService.loginUser(authLoginRequest);

            Cookie jwtCookie = new Cookie("jwt", authResponse.token());
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

    @PostMapping("/log-out")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                }
            }
            ;
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

    @GetMapping("/check-authentication")
    public ResponseEntity<Map<String, Boolean>> checkAuthentication(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        boolean isAuthenticated = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    isAuthenticated = jwtUtil.validate(cookie.getValue());
                    break;
                }
            }
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("authenticated", isAuthenticated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
