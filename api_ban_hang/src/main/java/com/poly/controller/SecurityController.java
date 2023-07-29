package com.poly.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @RequestMapping("/security/login/form")
    public String loginForm(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập");
        return "security/login";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(HttpServletRequest request,Model model) {
        model.addAttribute("request", request.getRemoteUser());
        model.addAttribute("dire", request.isUserInRole("DIRE"));
        model.addAttribute("staf", request.isUserInRole("STAF"));
        System.out.println(request.getRemoteUser());
        model.addAttribute("message", "Đăng nhập thành công");
        return "security/login";
    }

    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Đăng nhập thất bại");
        return "security/login";
    }

    @RequestMapping("/security/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message", "Không có quyền truy xuất");
        return "security/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logout(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất");
        return "security/login";
    }
    @RequestMapping("/security/**")
    public String some_page(HttpServletRequest request, Model model) {
        // Kiểm tra xem người dùng đã đăng nhập thành công hay chưa
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Đã đăng nhập, thực hiện các hành động cần thiết
            model.addAttribute("request", authentication.getName());
            model.addAttribute("dire", authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("DIRE")));
            model.addAttribute("staf", authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("STAF")));
        } else {
            // Chưa đăng nhập, làm gì đó khác nếu cần thiết
        }

        model.addAttribute("message", "Đăng nhập thành công");
        return "security/login";
    }
}
