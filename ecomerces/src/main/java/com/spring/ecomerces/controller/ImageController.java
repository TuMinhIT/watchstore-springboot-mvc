package com.spring.ecomerces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;

@Controller
public class ImageController {

    private final String uploadDir = "images/";

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) {
        try {
            Path filePath = Paths.get(uploadDir + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
            model.addAttribute("uploadMessage", "Image uploaded successfully!");
        } catch (Exception e) {
            model.addAttribute("uploadMessage", "Image upload failed!");
        }
        return "/admin/product"; // Return the Thymeleaf template name
    }
}
