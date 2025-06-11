package com.example.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecommerce.entity.Customer;

public interface CustomerRepo extends MongoRepository<Customer, String>{

	Optional<Customer> findByUsername(String userame);
}
