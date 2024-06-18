package com.gym.demo.security.config.Auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"username", "message", "status" , "jwtcookie"})
public record AuthResponse(String username,String message, String token , Boolean status , String role) {



}
