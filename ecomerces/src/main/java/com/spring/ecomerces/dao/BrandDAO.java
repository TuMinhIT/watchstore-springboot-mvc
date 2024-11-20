package com.spring.ecomerces.dao;

import com.spring.ecomerces.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandDAO extends JpaRepository<Brand, Integer>{
    Brand findByName(String name);
    boolean existsBrandByName(String name);

    @Query("SELECT b.name FROM Brand b")
    List<String> getAllBrandNames();

}
