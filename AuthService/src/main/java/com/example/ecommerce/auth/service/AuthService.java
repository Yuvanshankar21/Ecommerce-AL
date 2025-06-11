package com.example.ecommerce.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.CustomerClient;
import com.example.ecommerce.auth.dto.AuthRequest;
import com.example.ecommerce.auth.dto.Customer;
import com.example.ecommerce.auth.dto.RegisterRequest;
import com.example.ecommerce.auth.utils.JwtUtil;

import feign.FeignException;

@Service
public class AuthService {

    @Autowired
    private CustomerClient custCli;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    public String login(AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return jwtUtil.generateToken(request.getUsername(), request.getRole());
    }

    public String register(RegisterRequest request) {
        try {
            // Check if user already exists
            Customer existing = custCli.getCustomerByUsername(request.getUsername());
            if (existing != null) {
                throw new RuntimeException("User already exists");
            }
        } catch (FeignException.NotFound e) {
            // This is expected: user doesn't exist — so we can proceed to create
        }

        Customer customer = new Customer(
            "", 
            request.getUsername(),
            passwordEncoder.encode(request.getPassword()),
            request.getEmail(),
            request.getRole()
        );

        ResponseEntity<Customer> response = custCli.create(customer); // Call to customer microservice to save new customer

        return jwtUtil.generateToken(response.getBody().getUsername(), response.getBody().getRole());
    }
}
