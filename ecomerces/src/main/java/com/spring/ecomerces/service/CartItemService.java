package com.spring.ecomerces.service;

import com.spring.ecomerces.models.CartItem;

import java.util.List;

public interface CartItemService {

    void removeCartItem(int cartItemId);
    void updateCartItemQuantity(int cartItemId,int  quantity);
}
