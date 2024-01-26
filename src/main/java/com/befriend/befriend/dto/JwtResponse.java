package com.befriend.befriend.dto;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    // You can add more fields as needed, such as userId, username, email, roles, etc.

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter and Setter for token
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Getter for type (you probably don't need a setter for the type as it's constant)
    public String getType() {
        return type;
    }
    
    // Add getters and setters for any other fields you add
}