package com.shubham.shoppershub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping({"/admin","/admin/index","/admin/index.html"})
    public String adminPage() {
        return "admin/index";
    }
}
