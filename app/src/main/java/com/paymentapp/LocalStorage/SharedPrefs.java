package com.paymentapp.LocalStorage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static final String PREFS_NAME = "auth_prefs";
    private static final String KEY_AUTH_TOKEN = "auth_token";
    private static final String KEY_ID = "id";

    // Method to save the auth token
    public static void saveAuthToken(Context context, String authToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_AUTH_TOKEN, authToken);
        editor.apply();
    }
    // Method to retrieve the auth token
    public static String getAuthToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AUTH_TOKEN, null);
    }

    // Method to save the payment ID
    public static void saveID(Context context, int id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, id);
        editor.apply();
    }
    // Method to retrieve the payment ID
    public static Integer getID(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_ID, 0);
    }

}
