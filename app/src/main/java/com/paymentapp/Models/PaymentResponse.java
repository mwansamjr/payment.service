package com.paymentapp.Models;

public class PaymentResponse {

    private Payment payment;

    public PaymentResponse() {
    }

    public PaymentResponse(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "payment=" + payment +
                '}';
    }
}
