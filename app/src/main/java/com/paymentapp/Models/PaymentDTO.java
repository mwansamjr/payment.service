package com.paymentapp.Models;

import java.math.BigDecimal;

public class PaymentDTO {

    private int id;
    private BigDecimal amount;
    private String status;
    private String message;

    public PaymentDTO() {
    }

    public PaymentDTO(int id, BigDecimal amount, String status, String message) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
