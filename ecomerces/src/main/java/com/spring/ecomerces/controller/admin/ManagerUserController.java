package com.spring.ecomerces.controller.admin;

import com.spring.ecomerces.models.Brand;
import com.spring.ecomerces.models.User;
import com.spring.ecomerces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin/users")
public class ManagerUserController {

    private UserService userService;
    @Autowired
    public ManagerUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("listUser", userService.findAllUsersExcludingAdmin());
        return "admin/users";
    }

    @PostMapping("/delete/{email}")
    public String deleteBrand(@PathVariable("email") String email, RedirectAttributes redirectAttributes) {

        User user= userService.findUserByEmail(email);
        userService.deleteUser(user);
        redirectAttributes.addFlashAttribute("message", "success!");
        return "redirect:/admin/users";
    }

}
