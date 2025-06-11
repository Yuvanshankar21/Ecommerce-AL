package com.example.ecommerce.auth.dto;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.ecommerce.auth.utils.Roles;

@Document(collection = "customer")
public class Customer {

	@Id
	private String id;
	private String username;
	private String pass;
	private String email;
	private Roles role; 
	
	public Customer() {
    }
	
	public Customer(String id,String username, String pass, String email, Roles role) {
		super();
		this.id=id;
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
	
	
	
}
