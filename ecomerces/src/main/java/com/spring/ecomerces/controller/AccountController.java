package com.spring.ecomerces.controller;

import com.spring.ecomerces.dto.UserDto;
import com.spring.ecomerces.models.User;
import com.spring.ecomerces.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/account")
public class AccountController {
    private UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    public String account(Model model) {
        return "redirect:/getAccountInfo";
    }

    @GetMapping("/getAccountInfo")
    public String showAccount(){
        return "account";
    }

    @PostMapping("/addAddress")
    public String addAddress(@RequestParam("new-address") String address, HttpSession session, RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/logging";
        }

        User user1 = userService.findUserByEmail(user.getEmail());
        if (user1 != null) {
            user.setAddress(address);
            userService.saveUser(user);
            session.setAttribute("user", user);
            redirectAttributes.addFlashAttribute("message", "đổi address thành công rồi nha");
        }
        return "redirect:/account/getAccountInfo";
    }


    @GetMapping("/deleteAddress")
    public String deleteAddress(HttpSession session, RedirectAttributes redirectAttribute) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        User user = userService.findUserByEmail(userDto.getEmail());
        user.setAddress(null);
        userService.saveUser(user);
        session.setAttribute("user", userDto.toDto(user));
        return "redirect:/account/getAccountInfo";
    }

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam("current-password") String currentPassword,
            @RequestParam("new-password") String newPassword,
            @RequestParam("confirm-password") String confirmPassword,
            Model model, HttpSession httpSession, RedirectAttributes redirectAttributes) {

        User user = (User) httpSession.getAttribute("user");

        User user1 = userService.findUserByEmail(user.getEmail());

        if (!userService.isValidCurrentPassword(currentPassword, user)) {
            redirectAttributes.addFlashAttribute("error", "Mật khẩu hiện tại không đúng");
            return "redirect:/account/getAccountInfo";
        }
        else {
            user.setPassword(newPassword);
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("message", "Đổi mật khẩu thành công");
        }
        return "redirect:/account/getAccountInfo";
    }

}







