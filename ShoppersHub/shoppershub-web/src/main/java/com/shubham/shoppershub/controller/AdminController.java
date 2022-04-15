package com.shubham.shoppershub.controller;

import com.shubham.shoppershub.entity.User;
import com.shubham.shoppershub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

//    @RequestMapping({"","/","/index","/index.html"})
//    public String adminPage() {
//        return "admin/index";
//    }

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/show/{id}")
    public String showAdminUserById(@PathVariable String id, Model model) {
        model.addAttribute("admin", userService.getUserById(Long.valueOf(id)));
        return "admin/index";
    }

    @RequestMapping("/register")
    public String newAdminUser(Model model) {
        model.addAttribute("admin", new User());
        return "admin/register";
    }

    @PostMapping("/save")
    public String saveOrUpdateAdminUser(@ModelAttribute("user") User user) {
        User saveAdminUser = userService.saveAdminUser(user);
        return "redirect:/admin/show/" + saveAdminUser.getId();
    }
}
