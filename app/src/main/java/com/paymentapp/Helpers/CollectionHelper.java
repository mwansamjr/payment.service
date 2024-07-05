package com.paymentapp.Helpers;

import android.content.Context;
import android.widget.Toast;

import com.paymentapp.ApiClient.ApiClient;
import com.paymentapp.LocalStorage.AuthTokenManager;
import com.paymentapp.Models.PaymentRequest;
import com.paymentapp.Models.PaymentResponse;
import com.paymentapp.Models.Token;
import com.paymentapp.Services.PaymentService;
import com.paymentapp.Services.PaymentServiceWithAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionHelper {
    private static final String TUMENY_API_SECRET = "abc";
    private static final String TUMENY_API_KEY = "efg";

    public CollectionHelper() {
    }

    public void generateToken(Context context) {
        // Instance without Authorization Headers
        PaymentService paymentService = ApiClient.getClientWithoutAuth().create(PaymentService.class);
        Call<Token> call = paymentService.generateToken(TUMENY_API_KEY,TUMENY_API_SECRET);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token = response.body();
                AuthTokenManager.saveAuthToken(context, "your_auth_token_here");
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(context, "Token Generation Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void requestPayment(PaymentRequest paymentRequest, Context context) {
        // Retrieve the auth token
        String authToken = AuthTokenManager.getAuthToken(context);
        // Instance with Authorization
        PaymentServiceWithAuth paymentServiceWithAuth = ApiClient.getClientWithAuth(authToken).create(PaymentServiceWithAuth.class);
        Call<PaymentResponse> call = paymentServiceWithAuth.requestPayment(paymentRequest);
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                PaymentResponse paymentResponse = response.body();
                // Store Id in local Storage
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {

            }
        });
    }

    public void getPaymentStatus(Context context) {
        String authToken = AuthTokenManager.getAuthToken(context);
        // Instance with Authorization
        PaymentServiceWithAuth paymentServiceWithAuth = ApiClient.getClientWithAuth(authToken).create(PaymentServiceWithAuth.class);
        // Retrieve Id from local storage
        Call<PaymentResponse> call = paymentServiceWithAuth.getPaymentStatus(1);
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                PaymentResponse paymentResponse = response.body();
                //Payment response stored or returned to user
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {

            }
        });
    }

}
