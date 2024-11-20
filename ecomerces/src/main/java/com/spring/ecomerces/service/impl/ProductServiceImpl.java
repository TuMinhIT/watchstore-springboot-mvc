package com.spring.ecomerces.service.impl;

import com.spring.ecomerces.dao.ProductDAO;
import com.spring.ecomerces.models.Brand;
import com.spring.ecomerces.models.Product;
import com.spring.ecomerces.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    ProductDAO productDAO;

    @Autowired
    ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll(Sort.by(Sort.Order.asc("id")));
    }

    @Override
    public boolean existsProductByName(String name) {
        return productDAO.existsProductByName(name);
    }


    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Override
    public void saveProduct(Product product, MultipartFile image) throws IOException {

        // Define the paths
        Path staticPath = Paths.get("src/main/resources/static");
        Path imagePath = Paths.get("images", "product");

        // Full path where the image will be saved
        Path fullImagePath = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath);

        if (!Files.exists(fullImagePath)) {
            Files.createDirectories(fullImagePath);
        }

        String originalFilename = image.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("Image file must have a valid name");
        }
//******ht
        String newFilename = "productImage_" + product.getId()+ "_" + originalFilename;

        Path file = fullImagePath.resolve(newFilename);
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        product.setImage("images/product/" + newFilename);
        productDAO.save(product);
    }


    @Override
    public void deleteProduct(int id) {
        Product product = productDAO.findById(id);
        // Delete the associated image file if it exists
        if (product != null && product.getImage() != null) {
            Path imagePath = Paths.get("src/main/resources/static", product.getImage());
            File imageFile = imagePath.toFile();
            if (imageFile.exists()) {
                boolean deleted = imageFile.delete();
                if (!deleted) {
                    System.out.println("Failed to delete the image: " + imageFile.getAbsolutePath());
                }
            }
        }
        productDAO.deleteById(id);
    }

    @Override
    public Product findById(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    public boolean updateProduct(Product product, MultipartFile file) throws IOException {
        if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().trim().isEmpty()) {
            productDAO.save(product);
            return true;
        }

        if (product != null && product.getImage() != null) {
            Path imagePath = Paths.get("src/main/resources/static", product.getImage());
            File imageFile = imagePath.toFile();
            if (imageFile.exists()) {
                boolean deleted = imageFile.delete();
                if (!deleted) {
                    System.out.println("Failed to delete the image: " + imageFile.getAbsolutePath());
                }
            }
        }
        saveProduct(product, file);
        return false;
    }

    @Override
    public List<Product> getProductsByBrand(Brand brand) {
        return productDAO.findAllByBrand(brand);
    }

    @Override
    public List<Product> searchByName(String productName) {
        return productDAO.findByNameContainingIgnoreCase(productName);
    }

    @Override
    public List<Product> searchByGender(String gender) {
        return productDAO.findByGender(gender);
    }
}
