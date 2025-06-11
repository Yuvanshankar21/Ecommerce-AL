package com.example.ecommerce.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderedItem;
import com.example.ecommerce.repository.OrderRepo;
import com.example.ecommerce.service.OrderService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;

class OrderServiceTest {
	@InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder_Success() {
        // Arrange
        OrderedItem item = new OrderedItem();
        item.setProductId("p123");
        item.setQuantity(2);

        Order order = new Order();
        order.setId("o001");
        order.setCustomerId("c001");
        order.setOrderDate(new Date());
        order.setItems(List.of(item));
        order.setStatus("PLACED");

        when(orderRepo.save(any(Order.class))).thenReturn(order);

        // Act
        Order result = orderService.placeOrder(order);

        // Assert
        assertNotNull(result);
        assertEquals("o001", result.getId());
        assertEquals("c001", result.getCustomerId());
        assertEquals("2024-01-01", result.getOrderDate());
        assertEquals("PLACED", result.getStatus());
        assertEquals(1, result.getItems().size());
        assertEquals("p123", result.getItems().get(0).getProductId());

        verify(orderRepo, times(1)).save(order);
        verify(kafkaTemplate, times(1)).send("order-events", "Order placed: o001");
    }
}
