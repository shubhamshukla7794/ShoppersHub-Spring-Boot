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

    @GetMapping("/home/{id}")
    public String showAdminUserById(@PathVariable Long id, Model model) {
        model.addAttribute("admin", userService.getUserById(id));
        return "admin/index";
    }

    @GetMapping("/profile/{id}")
    public String showAdminUserProfileById(@PathVariable Long id, Model model) {
        model.addAttribute("admin", userService.getUserById(id));
        return "admin/profile";
    }

    @GetMapping("/register")
    public String newAdminUser(Model model) {
        model.addAttribute("msg", "Register");
        model.addAttribute("admin", new User());
        return "admin/register";
    }

    @GetMapping("/update/{id}")
    public String updateAdminUser(@PathVariable Long id, Model model) {
        model.addAttribute("msg", "Update your profile");
        model.addAttribute("admin", userService.getUserById(id));
        return "admin/register";
    }

    @PostMapping("/save")
    public String saveOrUpdateAdminUser(@ModelAttribute("user") User user) {
        User saveAdminUser = userService.saveAdminUser(user);
        return "redirect:/admin/home/" + saveAdminUser.getId();
    }
}
