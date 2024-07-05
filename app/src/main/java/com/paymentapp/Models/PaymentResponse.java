package com.paymentapp.Models;

public class PaymentResponse {

    private PaymentDTO paymentDTO;

    public PaymentResponse() {
    }

    public PaymentResponse(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }
}
