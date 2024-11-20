package com.spring.ecomerces.service.impl;

import com.spring.ecomerces.dao.OrderDAO;
import com.spring.ecomerces.models.Order;
import com.spring.ecomerces.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.save(order);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderDAO.findByStatus(status);
    }

    @Override
    public Order getOrderById(int id) {
        return orderDAO.findById(id);
    }

    @Override
    public void deleteOrderById(int id) {
        orderDAO.deleteById(id);
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderDAO.findByUserId(customerId);
    }

}
