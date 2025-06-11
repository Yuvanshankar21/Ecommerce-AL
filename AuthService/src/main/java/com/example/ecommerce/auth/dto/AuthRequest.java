package com.example.ecommerce.auth.dto;

import com.example.ecommerce.auth.utils.Roles;

public class AuthRequest {

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	private String username;
    private String password;
    private Roles role;
}
