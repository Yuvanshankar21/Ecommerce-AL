package com.example.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepo;

	public Product save(Product product) {
        return productRepo.save(product);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(String id) {
        return productRepo.findById(id).orElseThrow();
    }

    public void deleteById(String id) {
        productRepo.deleteById(id);
    }
    
}
