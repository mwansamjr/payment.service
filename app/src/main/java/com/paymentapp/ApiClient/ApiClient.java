package com.paymentapp.ApiClient;

import com.paymentapp.OkHttpInterceptor.AuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit withAuth = null;
    private static Retrofit withoutAuth = null;

    public static Retrofit getClientWithAuth(String authToken) {
        if (withAuth == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(authToken))
                    .build();
            withAuth = new Retrofit.Builder()
                    .baseUrl("https://tumeny.herokuapp.com/api")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return withAuth;
    }

    public static Retrofit getClientWithoutAuth() {
        if (withoutAuth == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            withoutAuth = new Retrofit.Builder()
                    .baseUrl("https://tumeny.herokuapp.com/api")  // Replace with your base URL
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return withoutAuth;
    }
}
