package com.spring.ecomerces.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table
@ToString(exclude = "products")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int status;
    private String image ;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;


}
