package com.spring.ecomerces.service;

import com.spring.ecomerces.models.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    List<Order> getOrdersByStatus(String status);
    Order getOrderById(int id);
    void deleteOrderById(int id);
    List<Order> getOrdersByCustomerId(int customerId);
}
