package com.example.ecommerce.auth.dto;

public class AuthResponse {
    private String token;

    public AuthResponse() {} // Required for serialization

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
