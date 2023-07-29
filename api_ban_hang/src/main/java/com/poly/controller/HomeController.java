package com.poly.controller;

import com.poly.entity.Account;
import com.poly.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping({"/", "/home/index"})
    public String home() {
        return "redirect:/product/list";
    }

    @RequestMapping({"/admin", "/admin/home/index"})
    public String admin() {
        return "redirect:/assets/admin/index.html";
    }


}
