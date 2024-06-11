package com.gym.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gym.demo.models.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    List<Payment> findByUserDni(String dni);

}
