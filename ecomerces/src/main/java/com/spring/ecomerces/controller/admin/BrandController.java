package com.spring.ecomerces.controller.admin;

import com.spring.ecomerces.models.Brand;
import com.spring.ecomerces.models.Product;
import com.spring.ecomerces.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping( "/admin/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/addNew")
    public String saveBrand(@ModelAttribute("brand") Brand brand,
                            @RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) {
        if (brandService.existsBrandByName(brand.getName())){
            redirectAttributes.addFlashAttribute("error", "Đã tồn tại thương hiệu này");
            return "redirect:/admin/brand";
        }

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please select a file to upload.");
            return "redirect:/admin/brand";
        }
        try {
            brand.setStatus(1);
            brandService.saveBrand(brand, file);
            redirectAttributes.addFlashAttribute("message", "Thêm thương hiệu thành công!");
            return "redirect:/admin/brand";

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Error uploading product image.");
            e.printStackTrace();
        }
        return "redirect:/admin/brand";
    }


    @PostMapping("/edit/{id}")
    public String UpdateBrand(@ModelAttribute("brandEdit") Brand brand,
                              @RequestParam("editFile") MultipartFile file,
                              RedirectAttributes redirectAttributes) throws IOException {
        brandService.updateBrand(brand,file);
        redirectAttributes.addFlashAttribute("message", "Cập nhật thương hiệu thành công!");
        return "redirect:/admin/brand";
    }


    // Hiển thị form chỉnh sửa thương hiệu
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Brand brand = brandService.getBrandById(id);
        if (brand != null) {
            redirectAttributes.addFlashAttribute("brandEdit", brand);
        } else {
            redirectAttributes.addFlashAttribute("error", "Đã có lỗi xảy ra");
        }
        return "redirect:/admin/brand";
    }

    // Xử lý yêu cầu xóa thương hiệu
    @PostMapping("/delete/{id}")
    public String deleteBrand(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        brandService.deleteBrandById(Math.toIntExact(id));
        redirectAttributes.addFlashAttribute("message", "Xóa thương hiệu thành công!");
        return "redirect:/admin/brand";
    }
}
