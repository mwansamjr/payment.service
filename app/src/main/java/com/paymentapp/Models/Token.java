package com.paymentapp.Models;

public class Token {
    private String token;
    private ExpireAtDTO expireAt;

    public Token() {
    }

    public Token(String token, ExpireAtDTO expireAt) {
        this.token = token;
        this.expireAt = expireAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ExpireAtDTO getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(ExpireAtDTO expireAt) {
        this.expireAt = expireAt;
    }
}
