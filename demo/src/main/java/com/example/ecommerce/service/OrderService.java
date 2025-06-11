package com.example.ecommerce.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	ProductService prodService;
	
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public Order placeOrder(Order order) {
		order.setStatus("Sucess");
        Order saved = orderRepo.save(order);
        saved.getItems().forEach(e->{
        	Product tempProd = prodService.findById(e.getProductId());
        	tempProd.setQuantity(e.getQuantity()-1);
        	prodService.save(tempProd);
        });
        logger.info("place order");
        kafkaTemplate.send("order-events", "Order placed: " + saved.getId());
        return saved;
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }
	
}
