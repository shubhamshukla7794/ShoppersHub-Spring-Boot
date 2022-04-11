package com.shubham.shoppershub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    @RequestMapping({"/category","/category/index","/category/index.html"})
    public String categoryPage() {
        return "category/index";
    }

}
