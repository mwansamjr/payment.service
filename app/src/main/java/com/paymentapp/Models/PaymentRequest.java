package com.paymentapp.Models;

import java.math.BigDecimal;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class PaymentRequest {
    private String description;
    private String customerFirstName;
    private String customerLastName;
    private String email;
    private String phoneNumber;
    private BigDecimal amount;

    public PaymentRequest(String description, String customerFirstName, String customerLastName, String email, String phoneNumber, BigDecimal amount) {
        this.description = description;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }
}

