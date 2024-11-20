package com.spring.ecomerces.models;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data

@ToString(exclude = {"user", "cart"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Người dùng đặt hàng

    @OneToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;  // Giỏ hàng tại thời điểm đ

    private int payment_amount;
    private String shipping_address;
    private String note;

    private String status; //Waiting cancel Completed

}
