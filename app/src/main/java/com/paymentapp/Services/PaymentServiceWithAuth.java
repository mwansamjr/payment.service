package com.paymentapp.Services;

import com.paymentapp.Models.PaymentRequest;
import com.paymentapp.Models.PaymentResponse;
import com.paymentapp.Models.SmsRequest;
import com.paymentapp.Models.SmsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaymentServiceWithAuth {
    @POST("v1/payment")
    Call<PaymentResponse> requestPayment(@Body PaymentRequest paymentRequest);

    @GET("api/v1/payment/{id}")
    Call<PaymentResponse> getPaymentStatus(@Path("id") int id);

    @POST("v1/sms/send")
    Call<SmsResponse> sendSms(@Body SmsRequest smsRequest);
}
