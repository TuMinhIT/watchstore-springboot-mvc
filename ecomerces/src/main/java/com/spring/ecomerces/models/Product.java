package com.spring.ecomerces.models;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "product")
@ToString(exclude =  {"brand", "cartItems"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long oldPrice;
    private long newPrice;
    private int stock;
    private String image;
    private String gender; //
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    public int getDiscount() {
        if (oldPrice > 0 && newPrice > 0) {
            return (int) Math.round(((double) (oldPrice - newPrice) / oldPrice) * 100);
        } else {
            return 0;
        }
    }
}
