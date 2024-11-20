package com.spring.ecomerces.service.impl;

import com.spring.ecomerces.dao.UserDAO;
import com.spring.ecomerces.models.Cart;
import com.spring.ecomerces.models.Order;
import com.spring.ecomerces.models.User;
import com.spring.ecomerces.service.CartService;
import com.spring.ecomerces.service.OrderService;
import com.spring.ecomerces.service.RoleService;
import com.spring.ecomerces.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;
    private CartService cartService;
    private OrderService orderService;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder,
                           CartService cartService, OrderService orderService, RoleService roleService) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.cartService = cartService;
        this.orderService = orderService;
        this.roleService = roleService;

    }

    @Override
    public List<User> findAllUser() {
        return userDAO.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public boolean isValidCurrentPassword(String currentPassword, User user) {
        return passwordEncoder.matches(currentPassword, user.getPassword());
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userDAO.deleteById(id);
    }

    @Override
    public List<User> findAllUsersExcludingAdmin() {
        return userDAO.findAllExcludingAdmin();
    }

    @Override
    public User userHasAccount(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword()))
            return user;
        return null;
    }

    @Override
    public void deleteUser(User user) {
        // Xóa tất cả các Cart liên quan
        for (Cart cart : user.getCarts()) {
            cartService.deleteCart(cart);
        }
        user.setCarts(null);

//        // Xóa tất cả các Order liên quan
//        for (Order order : user.getOrders()) {
//            orderService.deleteOrderById(order.getId());
//        }
//
//        // Gỡ bỏ Role liên kết
//        user.getRoles().clear();
//        userDAO.save(user); // Cập nhật lại User sau khi gỡ Role
//
//        // Xóa User
//        userDAO.delete(user);
    }

}
