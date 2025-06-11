package com.example.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.ecommerce.entity.Product;


public interface ProductRepo extends MongoRepository<Product, String>{
	
}
