package com.spring.ecomerces.dao;

import com.spring.ecomerces.models.CartItem;
import com.spring.ecomerces.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartItemDAO extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCartId(int cartId);

    CartItem findByCartIdAndProduct(int cartId, Product product);
}
