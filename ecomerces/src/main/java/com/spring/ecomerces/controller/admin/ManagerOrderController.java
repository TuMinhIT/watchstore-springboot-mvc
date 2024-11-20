package com.spring.ecomerces.controller.admin;

import com.spring.ecomerces.models.Brand;
import com.spring.ecomerces.models.Cart;
import com.spring.ecomerces.models.Order;
import com.spring.ecomerces.service.CartService;
import com.spring.ecomerces.service.OrderService;
import com.spring.ecomerces.service.ProductService;
import com.spring.ecomerces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping( "/admin/order")
public class ManagerOrderController {
    private OrderService orderService;
    private CartService cartService;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public ManagerOrderController(OrderService orderService, CartService cartService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public String orders(Model model) {
        List<Order> WaitingOrders = orderService.getOrdersByStatus("WAITING");
        model.addAttribute("WOrders", WaitingOrders);

        List<Order> COrders = orderService.getOrdersByStatus("CANCELLED");
        model.addAttribute("COrders", COrders);

        List<Order> POrders = orderService.getOrdersByStatus("COMPLETED");
        model.addAttribute("POrders", POrders);
        return "admin/confirmOrder";
    }


    @PostMapping("/confirm/{id}")
    public String UpdateBrand(@PathVariable("id") int id, RedirectAttributes redirectAttributes)  {
        Order order= orderService.getOrderById(id);
        order.setStatus("COMPLETED");
        order.getCart().setStatus("COMPLETED");
        orderService.addOrder(order);

        redirectAttributes.addFlashAttribute("message","confirm successful");
        return "redirect:/admin/order";
    }
}
