package com.spring.ecomerces.controller.admin;


import com.spring.ecomerces.models.Brand;
import com.spring.ecomerces.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private BrandService brandService;

   @Autowired
   public AdminController(BrandService brandService) {
       this.brandService = brandService;
   }

    @GetMapping("/home")
    public String admin() {
        return "admin/home";
    }

    @GetMapping("/brand")
    public String showAddBrandForm(Model model) {
        model.addAttribute("listBrand", brandService.getAllBrands());
        model.addAttribute("brand", new Brand());
        return "admin/brand";
    }

}
