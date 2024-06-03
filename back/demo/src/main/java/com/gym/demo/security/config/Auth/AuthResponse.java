package com.gym.demo.security.config.Auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.servlet.http.Cookie;

@JsonPropertyOrder({"username", "message", "jwt", "status" , "jwtcookie"})
public record AuthResponse(String username,String message, String jwt , Boolean status , Cookie jwtcookie , String role) {



}
