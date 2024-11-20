package com.spring.ecomerces.controller;

import com.spring.ecomerces.models.Cart;
import com.spring.ecomerces.models.Order;
import com.spring.ecomerces.models.User;
import com.spring.ecomerces.service.CartService;
import com.spring.ecomerces.service.OrderService;
import com.spring.ecomerces.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private CartService cartService;
    private UserService userService;
    @Autowired
    public OrderController(OrderService orderService, CartService cartService, UserService userService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public String order(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");

        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("cart",cart);
        return "order";
    }

    @PostMapping("/addOrder")
    public String addOrder(@ModelAttribute("user") User user1,
                           @RequestParam("note") String note,
                           Model model, @AuthenticationPrincipal UserDetails userDetails,
                           RedirectAttributes redirectAttributes) {


       User user = userService.findUserByEmail(userDetails.getUsername());

       Cart cart = cartService.findCartStatus(user,"ACTIVE");

        Order order = new Order();
        order.setUser(user);
        order.setShipping_address(user1.getAddress());
        order.setStatus("WAITING");

        cart.setStatus("COMPLETE");
        cartService.saveCart(cart);

        order.setNote(note);
        order.setCart(cart);
        order.setPayment_amount( cart.getFee() + cart.getTotal());

        orderService.addOrder(order);
        user.getOrders().add(order);
        redirectAttributes.addFlashAttribute("orderSuccess", "successfully");

        return "redirect:/order";
    }

}
