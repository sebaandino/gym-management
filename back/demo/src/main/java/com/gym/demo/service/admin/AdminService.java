package com.gym.demo.service.admin;

import java.util.List;

import com.gym.demo.dtos.RutinaDiaDto;
import com.gym.demo.dtos.UserEntityDto;
import com.gym.demo.models.Payment;

public interface AdminService {

    void save(UserEntityDto userEntityDto);

    UserEntityDto findByDni(String dni);

    List<UserEntityDto> findAll();

    void setRutine(String dni, List<RutinaDiaDto> rutinaDiaDto);

    String findRutinaByDni(String dni);

    void delete(String id);

    List<Payment> findPaymentsByDni(String dni);

    Payment addPayment(String dni);

}
