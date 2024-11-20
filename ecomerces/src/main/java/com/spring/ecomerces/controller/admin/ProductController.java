package com.spring.ecomerces.controller.admin;

import com.spring.ecomerces.models.Product;
import com.spring.ecomerces.service.BrandService;
import com.spring.ecomerces.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;



@Controller
@RequestMapping("/admin/product")
public class ProductController {
    private ProductService productService;
    private BrandService brandService;
    @Autowired
    public ProductController(ProductService productService, BrandService brandService) {
        this.productService = productService;
        this.brandService = brandService;
    }

    @GetMapping
    public String showProduct(Model model) {
        model.addAttribute("listProduct", productService.getAllProducts());
        model.addAttribute("product", new Product());
        model.addAttribute("brand",brandService.getAllBrands());
        return "admin/product";
    }

    @GetMapping("/productDetail/{id}")
    public String showProductDetail(Model model, @PathVariable String id) {
        model.addAttribute("product", productService.getProductById(Integer.parseInt(id)));
        return "admin/productDetails";
    }

//    add new product
    @PostMapping("/addNew")
    public String addNewProduct(
        @ModelAttribute("product") Product product,
        @RequestParam("file") MultipartFile file,
        Model model, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please select a file to upload.");
            return "redirect:/admin/product";
        }
        try {
            productService.saveProduct(product, file);
            redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm mới thành công!");
            return "redirect:/admin/product";

        } catch (IOException e) {
            model.addAttribute("error", "Error uploading product image.");
            e.printStackTrace();
        }
        return "redirect:/admin/product";
    }

//    delete product by id
    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) throws IOException {
        productService.deleteProduct(id);
        return "redirect:/admin/product";
    }

//    edit product
    @GetMapping("/updateProduct/{id}")
    public String editProduct(@PathVariable int id, RedirectAttributes redirectAttributes) throws IOException  {
        Product product = productService.getProductById(id);
        redirectAttributes.addFlashAttribute("productEdit", product);
        redirectAttributes.addFlashAttribute("brands", brandService.getAllBrands());
        return "redirect:/admin/product";
    }

    @PostMapping("/updateProduct/{id}")
    public String updateProduct(@ModelAttribute("productEdit") Product product,
                                @RequestParam("editFile") MultipartFile file,
                                RedirectAttributes redirectAttributes) throws IOException {
            productService.updateProduct(product, file);
            redirectAttributes.addFlashAttribute("message", "Cập nhật thành công!");
            return "redirect:/admin/product";
    }

}
