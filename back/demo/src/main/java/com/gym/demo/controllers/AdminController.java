package com.gym.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.demo.dtos.UserEntityDto;
import com.gym.demo.service.admin.AdminServiceImp;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImp adminService;

    @GetMapping("/find/{dni}")
    public ResponseEntity<?> findUserbyDni(@PathParam("dni") String dni) {
        try {
            UserEntityDto userEntity = adminService.findByDni(dni);
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<?> findAllUsers() {
        try {
            return new ResponseEntity<>(adminService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> CreateUser(@RequestBody UserEntityDto usuarioDto) {
        adminService.save(usuarioDto);
        return new ResponseEntity<>("usuario creado", HttpStatus.OK);
    }
    

}
