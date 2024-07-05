package com.paymentapp.Models;

import java.math.BigDecimal;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class PaymentDTO {

    private int id;
    private BigDecimal amount;
    private String status;
    private String message;
}
