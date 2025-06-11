package com.example.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecommerce.entity.Order;

public interface OrderRepo extends MongoRepository<Order, String>{

}
