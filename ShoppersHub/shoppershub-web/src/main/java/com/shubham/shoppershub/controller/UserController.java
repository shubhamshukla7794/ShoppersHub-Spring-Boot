package com.shubham.shoppershub.controller;

import com.shubham.shoppershub.entity.User;
import com.shubham.shoppershub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/show/{id}")
    public String showUserById(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getUserById(Long.valueOf(id)));
        return "user/index";
    }

    @RequestMapping("user/register")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("user")
    public String saveOrUpdateUser(@ModelAttribute("user") User user) {
        User savedUser = userService.saveUser(user);
        return "redirect:/user/show/" + savedUser.getId();
    }
}
