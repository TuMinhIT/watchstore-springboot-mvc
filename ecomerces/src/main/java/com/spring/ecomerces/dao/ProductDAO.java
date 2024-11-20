package com.spring.ecomerces.dao;

import com.spring.ecomerces.models.Brand;
import com.spring.ecomerces.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductDAO extends JpaRepository<Product, Integer> {
    Product findByName(String name);
    Product findById(int id);
    boolean existsProductByName(String name);
    void deleteById(int id);
    Product getProductById(int id);
    List<Product> findAllByBrand(Brand brand);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> searchByName(String productName);
    List<Product> findByGender(String gender);
}
