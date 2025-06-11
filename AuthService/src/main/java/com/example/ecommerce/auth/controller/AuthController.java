package com.example.ecommerce.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.auth.dto.AuthRequest;
import com.example.ecommerce.auth.dto.AuthResponse;
import com.example.ecommerce.auth.dto.RegisterRequest;
import com.example.ecommerce.auth.service.AuthService;
import org.springframework.http.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	
	@Autowired
    private AuthService authService;
	
	@PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
	    String token = authService.register(request);
	    return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
	}
}
