package com.spring.ecomerces.service.impl;

import com.spring.ecomerces.dao.BrandDAO;
import com.spring.ecomerces.models.Brand;
import com.spring.ecomerces.models.Product;
import com.spring.ecomerces.service.BrandService;
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
public class BrandServiceImpl implements BrandService {

    private BrandDAO brandDAO;

    @Autowired
    public BrandServiceImpl(BrandDAO brandDAO) {
        this.brandDAO = brandDAO;
    }

    @Override
    public void saveBrand(Brand brand) {
        brandDAO.save(brand);
    }

    @Override
    public boolean existsBrandByName(String name) {
        return  brandDAO.existsBrandByName(name);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandDAO.findAll(Sort.by(Sort.Order.asc("id")));
    }

    @Override
    public void deleteBrandById(int id) {
        brandDAO.deleteById(id);
    }

    @Override
    public Brand getBrandById(int id) {
        return brandDAO.findById(id).get();
    }

    @Override
    public void updateBrand(Brand brand) {
        brandDAO.save(brand);
    }

    @Override
    public List<String> getAllBrandNames() {
        return brandDAO.getAllBrandNames();
    }

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Override
    public void saveBrand(Brand brand, MultipartFile image) throws IOException {

        // Define the paths
        Path staticPath = Paths.get("src/main/resources/static");
        Path imagePath = Paths.get("images", "brand");

        // Full path where the image will be saved
        Path fullImagePath = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath);

        if (!Files.exists(fullImagePath)) {
            Files.createDirectories(fullImagePath);
        }

        String originalFilename = image.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("Image file must have a valid name");
        }

        String newFilename = "Brand_" + brand.getName()+ "_" + originalFilename;

        Path file = fullImagePath.resolve(newFilename);
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        brand.setImage("images/brand/" + newFilename);
        brandDAO.save(brand);
    }

    @Override
    public void updateBrand(Brand brand, MultipartFile file) throws IOException {
        if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().trim().isEmpty()) {
            brandDAO.save(brand);
        }
        if (brand != null && brand.getImage() != null) {
            Path imagePath = Paths.get("src/main/resources/static", brand.getImage());
            File imageFile = imagePath.toFile();
            if (imageFile.exists()) {
                boolean deleted = imageFile.delete();
                if (!deleted) {
                    System.out.println("Failed to delete the image: " + imageFile.getAbsolutePath());
                }
            }
        }
        saveBrand(brand, file);
    }
}
