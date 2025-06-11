package com.example.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.service.CustomerService;

@RestController()
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/getByName/{username}")
	public ResponseEntity<Customer> getByUsername(@PathVariable String username) {
	    Optional<Customer> customer = customerService.findByName(username);
	    return customer.map(ResponseEntity::ok)
	                   .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
		customer.setId(null);
        return ResponseEntity.ok(customerService.save(customer));
    }
	

    @GetMapping
    public List<Customer> getAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable String id) {
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable String id, @RequestBody Customer customer) {
        customer.setId(id);
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        customerService.deleteById(id);
    }
}
