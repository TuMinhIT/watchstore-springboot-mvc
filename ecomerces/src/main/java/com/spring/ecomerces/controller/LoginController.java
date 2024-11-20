package com.spring.ecomerces.controller;

import com.spring.ecomerces.models.User;
import com.spring.ecomerces.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showLoginForm() {
        return "login";
    }

//    @PostMapping
//    public String login(
//            @RequestParam("email") String email,
//            @RequestParam("password") String password,
//            @RequestParam(required = false, name = "remember-me") String rememberMe,
//            RedirectAttributes redirectAttributes,
//            HttpSession session,
//            HttpServletResponse response) {
//
//        User user= userService.userHasAccount(email, password);
//
//        if ( user != null) {
//            session.setAttribute("username", email);
//            session.setAttribute("user", user);
//
//            System.out.println("login: " + user);
//
//            // If "Remember Me" is checked, create a cookie
//            if ("on".equals(rememberMe)) {
//                Cookie rememberCookie = new Cookie("username", email);
//                rememberCookie.setMaxAge(7 * 24 * 60 * 60);
//                rememberCookie.setPath("/");
//                response.addCookie(rememberCookie);
//            }
//            return "redirect:/home";
//        } else {
//            redirectAttributes.addFlashAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
//            return "redirect:/login";
//        }
//    }
}
