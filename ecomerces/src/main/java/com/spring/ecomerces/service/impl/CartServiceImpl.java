package com.spring.ecomerces.service.impl;

import com.spring.ecomerces.dao.CartDAO;
import com.spring.ecomerces.dao.CartItemDAO;
import com.spring.ecomerces.dao.ProductDAO;
import com.spring.ecomerces.dao.UserDAO;
import com.spring.ecomerces.models.Cart;
import com.spring.ecomerces.models.CartItem;
import com.spring.ecomerces.models.Product;
import com.spring.ecomerces.models.User;
import com.spring.ecomerces.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartDAO cartDAO;
    private CartItemDAO cartItemDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;

    @Autowired
    public CartServiceImpl(CartDAO cartDAO, CartItemDAO cartItemDAO, ProductDAO productDAO, UserDAO userDAO) {
        this.cartDAO = cartDAO;
        this.cartItemDAO = cartItemDAO;
        this.productDAO = productDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Cart addProductToCart(int userId, int productId, int quantity) {
        User user = userDAO.findById(userId);
        Product product = productDAO.findById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        // Lấy giỏ hàng của người dùng có trạng thái "ACTIVE", nếu không có thì tạo mới
        Cart cart = createCart(user);

        // Kiểm tra nếu sản phẩm đã có trong giỏ hàng
        CartItem cartItem = cartItemDAO.findByCartIdAndProduct(cart.getId(), product);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setPrice((int) product.getNewPrice());
        }

        cartItemDAO.save(cartItem);

        cart.addCartItem(cartItem);
        cartDAO.save(cart);

//        user.setCart(cart);
        userDAO.save(user);
        return cart;
    }

    public Cart getActiveCart(User user) {
        return cartDAO.findByUserAndStatus(user, "ACTIVE");
    }

    @Override
    public List<CartItem> getCartItems(User user) {
        Cart cart = getActiveCart(user);
        return cart != null ? cart.getCartItems() : null;
    }

    @Override
    public void deleteCartItem(int cartItemId) {
        CartItem cartItem = cartItemDAO.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        Cart cart = cartItem.getCart();
        cart.getCartItems().remove(cartItem);

        cartItemDAO.delete(cartItem);
//        cart.getUser().setCart(cart);
        cartDAO.save(cart);
    }

    @Override
    public void updateCartItemQuantity(int cartItemId, int quantity) {

        CartItem cartItem = cartItemDAO.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItem.setQuantity(quantity);

        Cart cart = cartItem.getCart();
        cartDAO.save(cart);
    }

    @Override
    public Cart createCart(User user) {
        // Kiểm tra nếu người dùng có giỏ hàng "ACTIVE" hay không
        Cart activeCart = cartDAO.findByUserAndStatus(user, "ACTIVE");

        if (activeCart != null) {
            // Trả về giỏ hàng "ACTIVE" hiện có nếu đã tồn tại
            return activeCart;
        }

        // Tạo giỏ hàng mới nếu chưa có giỏ hàng "ACTIVE"
        Cart cart = new Cart();

        cart.setUser(user);

        cart.setStatus("ACTIVE");
        cart.setCartItems(new ArrayList<>());

        cart = cartDAO.save(cart);
        user.getCarts().add(cart);

        return cart;
    }


    @Override
    public void saveCart(Cart cart) {
        cartDAO.save(cart);
    }

    @Override
    public Cart findCartStatus(User user, String status) {
        return cartDAO.findByUserAndStatus(user, status);
    }

    @Override
    public Cart findById(int cartId) {
        return cartDAO.findById(cartId);
    }

    @Override
    public void deleteCart(Cart cart) {
        List<CartItem> cartItems = cart.getCartItems();
        cartItemDAO.deleteAll(cartItems);
        cartDAO.delete(cart);
    }

}
