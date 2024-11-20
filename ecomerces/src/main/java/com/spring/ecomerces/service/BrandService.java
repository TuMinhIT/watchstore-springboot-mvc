package com.spring.ecomerces.service;

import com.spring.ecomerces.models.Brand;
import com.spring.ecomerces.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BrandService {
    void saveBrand(Brand brand);
    boolean existsBrandByName(String name);
    List<Brand> getAllBrands();
    void deleteBrandById(int id);
    Brand getBrandById(int id);
    void updateBrand(Brand brand);
    List<String> getAllBrandNames();

    void saveBrand(Brand brand, MultipartFile file) throws IOException;
    void updateBrand(Brand brand, MultipartFile file) throws IOException;
}
