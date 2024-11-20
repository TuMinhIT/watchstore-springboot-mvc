package com.spring.ecomerces.dao;

import com.spring.ecomerces.models.Cart;
import com.spring.ecomerces.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CartDAO extends JpaRepository<Cart, Integer> {
    Cart findByUserAndStatus(User user, String status);
    Cart findById(int id);
}
