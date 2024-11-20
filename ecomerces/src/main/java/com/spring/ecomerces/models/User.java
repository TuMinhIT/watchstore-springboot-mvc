package com.spring.ecomerces.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@ToString(exclude = {"roles"})
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password;

    private String email;

    private String phone;

    private String address;

     @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
     @JoinTable(
             name = "users_roles",
             joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
             inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
     )
     private List<Role> roles = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();


}
