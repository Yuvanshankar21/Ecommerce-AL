package com.example.ecommerce.auth.dto;

import com.example.ecommerce.auth.utils.Roles;

public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Roles role;

    public RegisterRequest() {} // Required for deserialization

    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
}
