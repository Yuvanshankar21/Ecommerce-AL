package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	 	@PostMapping("/create")
	    public ResponseEntity<Product> create(@RequestBody Product product) {
	        return ResponseEntity.ok(productService.save(product));
	    }

	    @GetMapping
	    public List<Product> getAll() {
	        return productService.findAll();
	    }

	    @GetMapping("/{id}")
	    public Product getById(@PathVariable String id) {
	        return productService.findById(id);
	    }
	    
//	    @PreAuthorize("hasAuthority('Admin')")
	    @PutMapping("/update/{id}")
	    public Product update(@PathVariable String id, @RequestBody Product product) {
	        product.setId(id);
	        return productService.save(product);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable String id) {
	        productService.deleteById(id);
	    }
	
}
