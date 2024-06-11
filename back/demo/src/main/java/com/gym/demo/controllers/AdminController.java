package com.gym.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.demo.dtos.RutinaDiaDto;
import com.gym.demo.dtos.UserEntityDto;
import com.gym.demo.models.Payment;
import com.gym.demo.service.admin.AdminServiceImp;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImp adminService;

    @GetMapping("/find")
    public ResponseEntity<?> findAllUsers() {
        try {
            return new ResponseEntity<>(adminService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{dni}")
    public ResponseEntity<?> findUserbyDni(@PathVariable("dni") String dni) {
        try {
            UserEntityDto userEntity = adminService.findByDni(dni);
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/rutine/{dni}")
    public ResponseEntity<?> setRutine(@PathVariable("dni") String dni, @RequestBody List<RutinaDiaDto> rutinaDiaDto) {
        try {
            adminService.setRutine(dni, rutinaDiaDto);
            return new ResponseEntity<>("rutina seteada", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rutine/{dni}")
    public ResponseEntity<?> findRutine(@PathVariable("dni") String dni) {
        try {
            UserEntityDto userEntity = adminService.findByDni(dni);
            return new ResponseEntity<>(userEntity.getRutina(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/payment/{dni}")
    public ResponseEntity<?> findPayment(@PathVariable("dni") String dni) {
        try {
            List<Payment> payments = adminService.findPaymentsByDni(dni);
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/payment/{dni}")
    public ResponseEntity<?> addPayment(@PathVariable("dni") String dni) {
        try {
            adminService.addPayment(dni);
            return new ResponseEntity<>("payment added", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}