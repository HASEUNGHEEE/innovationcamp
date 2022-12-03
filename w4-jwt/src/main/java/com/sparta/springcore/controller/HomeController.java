package com.sparta.springcore.controller;

import com.sparta.springcore.model.UserRole;
import com.sparta.springcore.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());

//            if (userDetails.getUser().getRole() == UserRole.ADMIN) {
//                model.addAttribute("admin_role", true);
//            }

            return "loginHome";
        }

        else {
            return "index";
        }
    }
}