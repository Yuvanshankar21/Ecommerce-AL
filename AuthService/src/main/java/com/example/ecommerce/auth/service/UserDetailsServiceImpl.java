package com.example.ecommerce.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.*;

import com.example.ecommerce.CustomerClient;
import com.example.ecommerce.auth.dto.Customer;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	CustomerClient custCli;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = custCli.getCustomerByUsername(username);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPass())
                .build();
	}
	
}
