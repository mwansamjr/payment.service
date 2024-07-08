package com.paymentapp.Helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.paymentapp.ApiClient.ApiClient;
import com.paymentapp.LocalStorage.SharedPrefs;
import com.paymentapp.Models.PaymentRequest;
import com.paymentapp.Models.PaymentResponse;
import com.paymentapp.Models.SmsRequest;
import com.paymentapp.Models.SmsResponse;
import com.paymentapp.Models.Token;
import com.paymentapp.Services.PaymentService;
import com.paymentapp.Services.PaymentServiceWithAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionHelper {
    //Read These from a file!
    private static final String TUMENY_API_KEY = "ef756a52-a622-403b-b698-1e58245f1381";
    private static final String TUMENY_API_SECRET = "8b3fd3504055c7bdc7109cc5df47e3a1bbe1e062";


    public CollectionHelper() {
    }

    public void generateToken(Context context, PaymentRequest paymentRequest) {
        PaymentService paymentService = ApiClient.getClientWithoutAuth().create(PaymentService.class);
        Call<Token> call = paymentService.generateToken(TUMENY_API_KEY,TUMENY_API_SECRET);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (!(response.body()==null)){
                    Token token = response.body();
                    SharedPrefs.saveAuthToken(context, token.getToken());

                    Toast.makeText(context, "Generated Token:  " + token.getToken(), Toast.LENGTH_LONG).show();
                    Log.d("Response: "," "+ response.body());

                    //Request Payment Call
                    requestPayment(context,paymentRequest);
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(context, "Token Generation Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d("TokenGenError","Token Generation Error: " + t.getLocalizedMessage());

            }
        });
    }

    public void requestPayment(Context context, PaymentRequest paymentRequest) {
        String authToken = SharedPrefs.getAuthToken(context);
        Toast.makeText(context,"Local Storage Token: " + authToken,Toast.LENGTH_LONG).show();
        Toast.makeText(context,"Passed Payment Request: " + paymentRequest,Toast.LENGTH_LONG).show();
        Log.d("Token", authToken);
        Log.d("PaymentRequest", paymentRequest.toString());

        PaymentServiceWithAuth paymentServiceWithAuth = ApiClient.getClientWithAuth(authToken).create(PaymentServiceWithAuth.class);
        Call<PaymentResponse> call = paymentServiceWithAuth.requestPayment(paymentRequest);
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                if (!(response.body()==null)) {
                    PaymentResponse paymentResponse = response.body();
                    SharedPrefs.saveID(context, paymentResponse.getPayment().getId());
                    Toast.makeText(context,"Payment Response: " + paymentResponse,Toast.LENGTH_LONG ).show();
                    Log.d("response","Payment Response:  " + paymentResponse);

                    //Get Payment Status Call
                    getPaymentStatus(context);

                }

            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                Toast.makeText(context, "Payment Request  Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void getPaymentStatus(Context context) {
        String authToken = SharedPrefs.getAuthToken(context);
        String paymentId = SharedPrefs.getID(context);
        PaymentServiceWithAuth paymentServiceWithAuth = ApiClient.getClientWithAuth(authToken).create(PaymentServiceWithAuth.class);
        Call<PaymentResponse> call = paymentServiceWithAuth.getPaymentStatus(paymentId);
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                if (!(response.body()==null)) {
                    PaymentResponse paymentResponse = response.body();
                    Toast.makeText(context, "Payment Status: \n " + paymentResponse.getPayment().getStatus() + "\n ____________", Toast.LENGTH_LONG).show();
                    Log.d("PayStatus","Payment Status: " + paymentResponse.getPayment().getStatus());
                }
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                Toast.makeText(context, "Payment Status  Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sendSms(Context context, SmsRequest smsRequest) {
        String authToken = SharedPrefs.getAuthToken(context);
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
