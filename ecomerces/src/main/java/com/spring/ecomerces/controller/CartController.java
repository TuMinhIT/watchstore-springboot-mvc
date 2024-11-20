package com.spring.ecomerces.controller;
import com.spring.ecomerces.models.Cart;
import com.spring.ecomerces.models.CartItem;
import com.spring.ecomerces.models.Order;
import com.spring.ecomerces.models.User;
import com.spring.ecomerces.service.CartItemService;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;
    private CartItemService cartItemService;
    private OrderService orderService;
    private UserService userService;

    @Autowired
    public CartController(CartService cartService, CartItemService cartItemService, OrderService orderService, UserService userService) {
        this.cartService = cartService;
        this.cartItemService= cartItemService;
        this.orderService=orderService;
        this.userService=userService;
    }

    @GetMapping
    public String viewCartItems(Model model, @AuthenticationPrincipal UserDetails userDetails, HttpSession session) {

        if (userDetails == null) {
            return "redirect:/login";
        }
        User user = userService.findUserByEmail(userDetails.getUsername());
        Cart newCart = cartService.createCart(user);
        if (newCart == null) {
            return "cart";
        }
        model.addAttribute("cart", newCart);
        List<CartItem> cartItems = Optional.ofNullable(newCart.getCartItems()).orElse(new ArrayList<>());
        cartItems.sort(Comparator.comparingLong(CartItem::getId));

        model.addAttribute("cartItems", cartItems);
        session.setAttribute("cart", newCart);
        session.setAttribute("user", user);

        List<Order> Orders = orderService.getOrdersByCustomerId(user.getId());
        List<Order> Wait = new ArrayList<>();
        List<Order> Cancelled = new ArrayList<>();
        List<Order> Completed = new ArrayList<>();

        for (Order order : Orders) {
            if ("WAITING".equals(order.getStatus())) {
                Wait.add(order);
            } else if ("CANCELLED".equals(order.getStatus())) {
                Cancelled.add(order);
            } else if ("COMPLETED".equals(order.getStatus())) {
                Completed.add(order);
            }
        }

        model.addAttribute("Wait", Wait);
        model.addAttribute("Cancel", Cancelled);
        model.addAttribute("Complete", Completed);
        return "cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id, Model model, RedirectAttributes redirectAttributes,@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        User user = userService.findUserByEmail(userDetails.getUsername());
        cartService.addProductToCart(user.getId(),id, 1);
        redirectAttributes.addFlashAttribute("success","add success");
        return "redirect:/home";
    }

    @GetMapping("/detail/{id}")
    public String addToCartDetail(@PathVariable int id, Model model, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        User user = userService.findUserByEmail(userDetails.getUsername());

        cartService.addProductToCart(user.getId(),id, 1);
        redirectAttributes.addFlashAttribute("success"," success");
        return "redirect:/home/product/"+id;
    }

    @GetMapping("/detailNow/{id}")
    public String addToCartDetailNow(@PathVariable int id, Model model, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        User user = userService.findUserByEmail(userDetails.getUsername());
        cartService.addProductToCart(user.getId(),id, 1);
        return "redirect:/cart";
    }


    //     Xóa một CartItem
    @PostMapping("/removeCartItem/{cartItemId}")
    public String removeCartItem(@PathVariable("cartItemId") int cartItemId, RedirectAttributes redirectAttributes) {
        cartService.deleteCartItem(cartItemId);
        redirectAttributes.addFlashAttribute("success"," success");
        return "redirect:/cart";
    }

//    chỉnh sửa sản phẩm
    @PostMapping("/updateQuantity/{cartItemId}")
    public String updateQuantity(@PathVariable("cartItemId") int cartItemId,
                                 @RequestParam("quantity") int quantity,
                                 @RequestParam("action") String action,
                                    RedirectAttributes redirectAttributes) {
        if ("increase".equals(action)) {
            quantity += 1;
        } else if ("decrease".equals(action) && quantity > 1) {
            quantity -= 1;
        }
        cartService.updateCartItemQuantity(cartItemId, quantity);
        redirectAttributes.addFlashAttribute("success"," success");
        return "redirect:/cart";
    }

}
