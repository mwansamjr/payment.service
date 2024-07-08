package com.paymentapp.Models;

public class Payment {
    //PaymentDTO
    private String id;
    private int amount;
    private String status;
    private String message;

    public Payment() {
    }

    public Payment(String id, int amount, String status, String message) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
