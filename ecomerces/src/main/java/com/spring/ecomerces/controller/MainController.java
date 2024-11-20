package com.spring.ecomerces.controller;

import com.spring.ecomerces.models.Brand;
import com.spring.ecomerces.models.Product;
import com.spring.ecomerces.service.BrandService;
import com.spring.ecomerces.service.ProductService;
import com.spring.ecomerces.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {
    private UserService userService;
    private ProductService productService;
    private BrandService brandService;

    @Autowired
    public MainController(UserService userService, ProductService productService, BrandService brandService) {
        this.userService = userService;
        this.productService = productService;
        this.brandService = brandService;
    }

    @GetMapping
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails, HttpSession session) {

        List<Product> products = productService.getAllProducts();
        model.addAttribute("productList", products);

        List<Brand> brandsList = brandService.getAllBrands();
        session.setAttribute("brands", brandsList);
        if (userDetails != null) {
            session.setAttribute("user", userDetails);
            model.addAttribute("username", userDetails.getUsername());
            if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                model.addAttribute("admin", true);
            }
            model.addAttribute("role", userDetails.getAuthorities());
        }
        return "main";
    }

    @GetMapping("/home/product/{id}")
    public String productDetail(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        List<Product> products = productService.getProductsByBrand(product.getBrand());
        model.addAttribute("product", product);
        model.addAttribute("productList", products);
        return "detail";
    }

    @GetMapping("/home/brand/{id}")
    public String allProductOfBrand(@PathVariable int id, Model model) {
        Brand brand = brandService.getBrandById(id);
        List<Product> products = productService.getProductsByBrand(brand);
        model.addAttribute("productList", products);
        model.addAttribute("brand", brand);

//        List<Product> allProduct = productService.getAllProducts();
//        model.addAttribute("allProduct", allProduct);

        List<Product> allProduct = productService.getAllProducts();
        Collections.shuffle(allProduct);
        List<Product> randomProducts = allProduct.stream().limit(10).collect(Collectors.toList());

        model.addAttribute("allProduct", randomProducts);

        return "AllProduct_Brand";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }


    @GetMapping("/home/search")
    public String search(@RequestParam("productName") String productName, Model model, RedirectAttributes redirectAttributes) {
        // Gọi phương thức tìm kiếm từ service
        List<Product> products = productService.searchByName(productName);
        if (products.isEmpty()) {
            redirectAttributes.addFlashAttribute("SearchError", "Product not found");
            return "redirect:/home";
        }
        model.addAttribute("productName", productName);
        model.addAttribute("searchProducts", products);
        return "searchProduct";
    }

    @GetMapping("/home/searchByGender")
    public String searchByGender(@RequestParam("gender") String gender,
                                 Model model, RedirectAttributes redirectAttributes) {

        List<Product> products = productService.searchByGender(gender);
        if (products.isEmpty()) {
            redirectAttributes.addFlashAttribute("SearchError", "Product not found");
            return "redirect:/home";
        }
        model.addAttribute("productName", gender);
        model.addAttribute("searchProducts",  products);
        return "searchProduct";
    }


}

