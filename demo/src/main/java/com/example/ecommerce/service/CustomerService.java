package com.example.ecommerce.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.repository.CustomerRepo;

@Service
public class CustomerService implements UserDetailsService{

	@Autowired
	CustomerRepo custRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public Customer save(Customer customer) {
        return custRepo.save(customer);
    }

    public List<Customer> findAll() {
        return custRepo.findAll();
    }

    public Customer findById(String id) {
        return custRepo.findById(id).orElseThrow();
    }

    public void deleteById(String id) {
        custRepo.deleteById(id);
    }
    
    public Optional<Customer> findByName(String name){
    	return custRepo.findByUsername(name);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Customer user = custRepo.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

	        return new org.springframework.security.core.userdetails.User(
	                user.getUsername(),
	                user.getPass(),
	                Collections.emptyList() // Or use roles if needed
	        );
	}
}
