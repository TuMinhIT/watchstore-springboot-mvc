package com.spring.ecomerces.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HeaderController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "main";
    }

}
