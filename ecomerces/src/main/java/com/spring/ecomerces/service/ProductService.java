package com.spring.ecomerces.service;

import com.spring.ecomerces.models.Brand;
import com.spring.ecomerces.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product getProductById(int id);
    List<Product> getAllProducts();
    boolean existsProductByName(String name);
    void saveProduct(Product product, MultipartFile file) throws IOException;
    void deleteProduct(int id);
    Product findById(int id);
    boolean updateProduct(Product product, MultipartFile file) throws IOException;
    List<Product> getProductsByBrand(Brand brand);
    List<Product> searchByName(String productName);
    List<Product> searchByGender(String gender);
}
