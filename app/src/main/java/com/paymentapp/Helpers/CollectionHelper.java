package com.paymentapp.Helpers;

import android.content.Context;
import android.widget.Toast;

import com.paymentapp.ApiClient.ApiClient;
import com.paymentapp.LocalStorage.SharedPrefs;
import com.paymentapp.Models.PaymentRequest;
import com.paymentapp.Models.PaymentResponse;
import com.paymentapp.Models.SmsRequest;
import com.paymentapp.Models.SmsResponse;
import com.paymentapp.Models.Token;
import com.paymentapp.PaymentCheckout.Paycheckout;
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
                if (!(response.body()==null)){
                    Token token = response.body();
                    SharedPrefs.saveAuthToken(context, token.getToken());
                }
                Toast.makeText(context, "Token:  " + response, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(context, "Token Generation Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void requestPayment(Context context, PaymentRequest paymentRequest) {
        String authToken = SharedPrefs.getAuthToken(context);
        PaymentServiceWithAuth paymentServiceWithAuth = ApiClient.getClientWithAuth(authToken).create(PaymentServiceWithAuth.class);
        Call<PaymentResponse> call = paymentServiceWithAuth.requestPayment(paymentRequest);
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                if (!(response.body()==null)) {
                    PaymentResponse paymentResponse = response.body();
                    SharedPrefs.saveID(context, paymentResponse.getPaymentDTO().getId());
                }
                Toast.makeText(context, "Payment Response: " + response, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                Toast.makeText(context, "Payment Request  Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void getPaymentStatus(Context context) {
        String authToken = SharedPrefs.getAuthToken(context);
        int paymentId = SharedPrefs.getID(context);
        PaymentServiceWithAuth paymentServiceWithAuth = ApiClient.getClientWithAuth(authToken).create(PaymentServiceWithAuth.class);
        Call<PaymentResponse> call = paymentServiceWithAuth.getPaymentStatus(paymentId);
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                if (!(response.body()==null)) {
                    PaymentResponse paymentResponse = response.body();
                }
                Toast.makeText(context, "Payment Response: " + response, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                Toast.makeText(context, "Payment Status  Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sendSms(Context context, SmsRequest smsRequest) {
        String authToken = SharedPrefs.getAuthToken(context);
        int paymentId = SharedPrefs.getID(context);
        PaymentServiceWithAuth paymentServiceWithAuth = ApiClient.getClientWithAuth(authToken).create(PaymentServiceWithAuth.class);
        Call<SmsResponse> call = paymentServiceWithAuth.sendSms(smsRequest);
        call.enqueue(new Callback<SmsResponse>() {
            @Override
            public void onResponse(Call<SmsResponse> call, Response<SmsResponse> response) {
                if (!(response.body()==null)) {
                    SmsResponse smsResponse = response.body();
                }
                Toast.makeText(context, "Sms Response: " + response, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<SmsResponse> call, Throwable t) {
                Toast.makeText(context, "Sms Request  Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
