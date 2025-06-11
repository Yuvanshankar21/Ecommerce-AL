package com.example.ecommerce;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ecommerce.auth.dto.Customer;

@FeignClient(name = "E-commerce")
public interface CustomerClient {

	@GetMapping("/customer/getByName/{username}")
    Customer getCustomerByUsername(@PathVariable("username") String username);
	
	@PostMapping("/customer/create")
    ResponseEntity<Customer> create(@RequestBody Customer customer);
}
