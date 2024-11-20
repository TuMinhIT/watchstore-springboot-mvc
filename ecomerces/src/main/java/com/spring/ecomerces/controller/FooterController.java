package com.spring.ecomerces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterController {

    @GetMapping("/introduce")
    public String introduce() {
        return "footer/introduce";
    }

    @GetMapping("/contact")
    public String contact() {
        return "footer/contact";
    }

    @GetMapping("/system")
    public String system() {
        return "footer/system";
    }

    @GetMapping("/instruction")
    public String instruction() {
        return "footer/instruction";
    }

    @GetMapping("/policy")
    public String policy() {
        return "footer/policy";
    }

    @GetMapping("/security")
    public String security() {
        return "footer/security";
    }

    @GetMapping("/delivery")
    public String delivery() {
        return "footer/delivery";
    }


}
