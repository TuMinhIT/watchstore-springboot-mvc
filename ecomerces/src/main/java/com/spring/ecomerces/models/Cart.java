package com.spring.ecomerces.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // `user_id` là khóa ngoại trỏ đến `User`
    private User user;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    private String status;  // "ACTIVE", "COMPLETED"

    private int fee = 40000;

    public int getTotal() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    // Phương thức thêm CartItem vào Cart
    public void addCartItem(CartItem cartItem) {
        cartItem.setCart(this);  // Liên kết CartItem với Cart hiện tại
        this.cartItems.add(cartItem); // Thêm CartItem vào danh sách
    }
}
