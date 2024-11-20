package com.spring.ecomerces.service.impl;

import com.spring.ecomerces.dao.CartDAO;
import com.spring.ecomerces.dao.CartItemDAO;
import com.spring.ecomerces.dao.ProductDAO;
import com.spring.ecomerces.dao.UserDAO;
import com.spring.ecomerces.models.Cart;
import com.spring.ecomerces.models.CartItem;
import com.spring.ecomerces.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {



    private CartItemDAO cartItemDAO;
    private ProductDAO productDAO;
    private CartDAO cartDAO;

    @Autowired
    public CartItemServiceImpl( CartItemDAO cartItemDAO, ProductDAO productDAO,CartDAO cartDAO) {

        this.cartItemDAO = cartItemDAO;
        this.productDAO = productDAO;
        this.cartDAO = cartDAO;
    }

    // Xóa một CartItem
    @Override
    public void removeCartItem(int cartItemId) {
        Optional<CartItem> cartItem = cartItemDAO.findById(cartItemId);
        // Lấy Cart và xóa CartItem khỏi
        Cart cart = cartItem.get().getCart();
        cart.getCartItems().remove(cartItem);

        // Lưu lại Cart sau khi xóa CartItem
        cartDAO.save(cart);
    }

    // Chỉnh sửa số lượng sản phẩm
    @Override
    public void updateCartItemQuantity(int cartItemId, int quantity) {
        CartItem cartItem = cartItemDAO.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("CartItem not found"));
        cartItem.setQuantity(quantity);
        cartItemDAO.save(cartItem);
    }
}
