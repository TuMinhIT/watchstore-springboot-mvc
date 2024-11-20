package com.spring.ecomerces.dao;

import com.spring.ecomerces.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO  extends JpaRepository<Order, Integer> {
//  Phương thức truy vấn để lấy đơn hàng theo trạng thái
    List<Order> findByStatus(String status);
    Order findById(int id);
    List<Order> findByUserId(int id);
}
