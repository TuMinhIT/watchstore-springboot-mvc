package com.spring.ecomerces.service;

import com.spring.ecomerces.models.Cart;
import com.spring.ecomerces.models.CartItem;
import com.spring.ecomerces.models.User;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart addProductToCart(int userId, int productId, int quantity);
    Cart getActiveCart(User user);
    List<CartItem> getCartItems(User user);
    void deleteCartItem(int cartItemId);
    void updateCartItemQuantity(int cartItemId, int quantity);
    Cart createCart(User user);
    void saveCart(Cart cart);
    Cart findCartStatus(User user, String status);
    Cart findById(int cartId);
    void deleteCart(Cart cart);
}
