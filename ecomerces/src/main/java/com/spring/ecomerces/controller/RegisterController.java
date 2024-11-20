package com.spring.ecomerces.controller;

import com.spring.ecomerces.models.Role;
import com.spring.ecomerces.models.User;
import com.spring.ecomerces.service.RoleService;
import com.spring.ecomerces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home")
public class RegisterController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public RegisterController(UserService userService, RoleService roleService ) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String addNew(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/register")
    public String addNewPost(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {

        User user1= userService.findUserByEmail(user.getEmail());
        if (user1 != null) {
            redirectAttributes.addFlashAttribute("error", "This email already exists");
            return "redirect:/home/register";
        }

        Role userRole = roleService.findByRoleName("USER");
        if (userRole == null) {
            userRole = new Role("USER");
            roleService.saveRole(userRole);
        }

        user.getRoles().add(userRole);
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "success");
        return "redirect:/home/register";
    }
}
