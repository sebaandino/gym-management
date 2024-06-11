package com.gym.demo.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payments")
@Builder
public class Payment {

    @Id
    private String id;

    private String userDni;
    private LocalDateTime paymentDate;
    private LocalDateTime nextPaymentDate;
}
