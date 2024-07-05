package com.paymentapp.Services;

import com.paymentapp.Models.Token;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PaymentService {

    @POST("api/token")
    Call<Token> generateToken(@Header("apiKey") String TUMENY_API_KEY, @Header("apiSecret") String TUMENY_API_SECRET);
}
